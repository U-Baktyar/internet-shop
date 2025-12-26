package shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import shop.dao.UserDao;
import shop.dto.SaveUserDto;
import shop.dto.UserDto;
import shop.entity.user.User;
import shop.mapper.UserMapper;
import shop.service.UserService;


@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(
            UserDao userDao,
            UserMapper userMapper,
            PasswordEncoder passwordEncoder)
    {
        this.userDao = userDao;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto getUserByLogin(String login) {
        return userMapper.toUserDto(userDao.findByLogin(login));
    }

    @Override
    public UserDto saveUser(SaveUserDto saveUserDto) {
        String hashedPassword = passwordEncoder.encode(saveUserDto.getPassword());
        User user = userMapper.toUser(saveUserDto);
        user.setPassword(hashedPassword);
        return userMapper.toUserDto(userDao.save(user));
    }


}
