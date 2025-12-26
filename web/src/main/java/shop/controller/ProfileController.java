package shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shop.dto.UserDto;
import shop.service.JwtService;
import shop.service.ProfileService;
import shop.service.UserService;

@RestController
public class ProfileController {

    private final UserService userService;
    private final ProfileService profileService;
    private final JwtService jwtService;

    @Autowired
    public ProfileController (
            UserService userService,
            ProfileService profileService,
            JwtService jwtService
    ){
        this.userService = userService;
        this.profileService = profileService;
        this.jwtService = jwtService;
    }

    @GetMapping("/myProfile")
    public UserDto getMyProfile(@RequestHeader("Authorization") String token){
        String login = jwtService.getLoginFromToken(token.substring(7));
        return userService.getUserByLogin(login);
    }

    @PutMapping("/updateProfile")
    public UserDto updateProfile(@RequestBody UserDto userDto, @RequestHeader("Authorization") String token){
        String login = jwtService.getLoginFromToken(token.substring(7));
        return profileService.updateUserProfile(userDto, login);
    }
}
