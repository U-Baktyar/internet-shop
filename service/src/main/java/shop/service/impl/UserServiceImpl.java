package shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.dao.UserDao;
import shop.dto.SaveUserDto;
import shop.dto.UpdateProfileUserDto;
import shop.dto.UserDto;
import shop.entity.user.User;
import shop.mapper.UserMapper;
import shop.service.UserService;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserDao userDao, UserMapper userMapper) {
        this.userDao = userDao;
        this.userMapper = userMapper;
    }

    @Override
    public UserDto getUserByLogin(String login) {
        return userMapper.toUserDto(userDao.findByLogin(login));
    }

    @Override
    public UserDto saveUser(SaveUserDto saveUserDto) {
        User user = userMapper.toUser(saveUserDto);
        return userMapper.toUserDto(userDao.save(user));
    }


}
