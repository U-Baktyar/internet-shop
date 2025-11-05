package shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shop.dto.SaveUserDto;
import shop.dto.UserDto;
import shop.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/find")
    public UserDto getUserByLogin(@RequestParam("login") String login) {
        return userService.getUserByLogin(login);
    }

    @PostMapping("/register")
    public UserDto SaveUser(@RequestBody SaveUserDto saveUserDto) {
        return userService.saveUser(saveUserDto);
    }
}
