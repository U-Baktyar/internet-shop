package shop.mapper;

import shop.dto.SaveUserDto;
import shop.dto.UpdateProfileUserDto;
import shop.dto.UserDto;
import shop.entity.user.User;

public interface UserMapper {
    UserDto toUserDto(User user);
    User toUser(SaveUserDto userDto);
}
