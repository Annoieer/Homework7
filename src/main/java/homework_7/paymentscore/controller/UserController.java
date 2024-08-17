package homework_7.paymentscore.controller;

import homework_7.paymentscore.dto.UserDto;
import homework_7.paymentscore.dto.UserResponseDto;
import homework_7.paymentscore.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/all")
    public UserResponseDto getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(value = "/{userId}")
    public UserDto getByUserId(@PathVariable Long userId) {
        return userService.getUser(userId);
    }
}
