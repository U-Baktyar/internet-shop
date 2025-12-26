package shop.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.dao.UserDao;
import shop.dto.UserDto;
import shop.entity.user.User;
import shop.mapper.UserMapper;
import shop.service.ProfileService;



@Service
public class ProfileServiceImpl implements ProfileService {

    private final UserDao userDao;
    private final UserMapper userMapper;


    public ProfileServiceImpl(UserDao userDao, UserMapper userMapper){
        this.userDao = userDao;
        this.userMapper = userMapper;
    }


    @Override
    @Transactional
    public UserDto updateUserProfile(UserDto userDto, String login) {
        User updateProfile = userMapper.toUserOnUpdate(userDto, userDao.findByLogin(login));
        return userMapper.toUserDto(userDao.save(updateProfile));
    }
}
