package shop.service;

import shop.dto.SaveUserDto;
import shop.dto.UpdateProfileUserDto;
import shop.dto.UserDto;
import shop.entity.user.User;

public interface UserService {
    UserDto getUserByLogin(String login);
    UserDto saveUser(SaveUserDto saveUserDto);
}
