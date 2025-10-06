package shop.mapper.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shop.dto.SaveUserDto;
import shop.dto.UpdateProfileUserDto;
import shop.dto.UserDto;
import shop.entity.user.User;
import shop.mapper.UserMapper;


@Component
public class UserMapperImpl implements UserMapper {

    @Autowired
    public UserMapperImpl() {}

    @Override
    public UserDto toUserDto(User user) {
        if (user == null) {
            return null;
        }
        UserDto userDto = new UserDto();
        userDto.setLogin(user.getLogin());
        userDto.setEmail(user.getEmail());
        userDto.setBirthday(user.getBirthDate());
        return userDto;
    }

    @Override
    public User toUser(SaveUserDto userDto) {
        if (userDto == null) {
            return null;
        }
        User user = new User();
        user.setLogin(userDto.getLogin());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setBirthDate(userDto.getBirthday());
        return user;
    }



}
