package unit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import shop.configuration.AppConfig;
import shop.dao.UserDao;
import shop.dto.SaveUserDto;

import shop.dto.UserDto;
import shop.entity.user.User;
import shop.mapper.UserMapper;
import shop.service.UserService;

import java.time.LocalDate;

import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AppConfig.class})
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockitoBean
    private UserDao userDao;
    @MockitoBean
    private UserMapper userMapper;
    @MockitoBean
    private PasswordEncoder passwordEncoder;

    @Test
    void saveUser_shouldEncodePassword_andReturnUserDto() {

        // Arrange
        SaveUserDto userDto = new SaveUserDto();
        userDto.setLogin("Aizek");
        userDto.setPassword("aizek");
        userDto.setEmail("aizek56@gmail.com");
        userDto.setBirthday(LocalDate.parse("1999-05-05"));

        User user = this.createTestUser(
                userDto.getLogin(),
                userDto.getEmail(),
                userDto.getBirthday()
        );
        user.setPassword(userDto.getPassword());

        when(passwordEncoder.encode(userDto.getPassword()))
                .thenReturn("aizek_encoded");
        when(userMapper.toUser(userDto))
                .thenReturn(user);

        UserDto resultUserDto = this.createTestUserDto(
                user.getLogin(),
                user.getEmail(),
                user.getBirthDate()
        );

        when(userDao.save(user))
                .thenReturn(user);
        when(userMapper.toUserDto(user))
                .thenReturn(resultUserDto);

        // Act
        UserDto res = userService.saveUser(userDto);

        // Assert
        assertNotNull(res);
        assertEquals("Aizek", res.getLogin());
        assertEquals("aizek56@gmail.com", res.getEmail());
        assertEquals(LocalDate.of(1999, 5, 5), res.getBirthday());

        verify(userDao).save(user);
        verify(userMapper).toUserDto(user);
        verify(passwordEncoder).encode("aizek");
        verifyNoMoreInteractions(userDao, userMapper, passwordEncoder);
    }

    @Test
    void getUserByLogin_shouldReturnUserDto() {

        // Arrange
        String login = "Jessika";
        User user = this.createTestUser(
                "Jessika",
                "jessika56@gmail.com",
                LocalDate.parse("1999-05-05")
        );

        UserDto resultUserDto = this.createTestUserDto(
                user.getLogin(),
                user.getEmail(),
                user.getBirthDate()
        );

        when(userDao.findByLogin(login))
                .thenReturn(user);
        when(userMapper.toUserDto(user))
                .thenReturn(resultUserDto);

        // Act
        UserDto result = userService.getUserByLogin(login);

        // Assert
        assertNotNull(result);
        assertEquals("Jessika", result.getLogin());
        assertEquals("jessika56@gmail.com", result.getEmail());
    }

    private User createTestUser(String login, String email, LocalDate birthday) {
        User user = new User();
        user.setLogin(login);
        user.setEmail(email);
        user.setBirthDate(birthday);
        return user;
    }

    private UserDto createTestUserDto(String login, String email, LocalDate birthday) {
        UserDto userDto = new UserDto();
        userDto.setLogin(login);
        userDto.setEmail(email);
        userDto.setBirthday(birthday);
        return userDto;
    }
}
