package shop.service;

import shop.dto.UserDto;

public interface ProfileService {

    UserDto updateUserProfile(UserDto userDto, String login);

}
