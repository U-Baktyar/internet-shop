package shop.service;

import shop.dto.SaveUserDto;
import shop.dto.UserDto;

public interface UserService {
    UserDto getUserByLogin(String login);
    UserDto saveUser(SaveUserDto saveUserDto);
}
