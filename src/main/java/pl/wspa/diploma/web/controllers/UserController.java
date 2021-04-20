package pl.wspa.diploma.web.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.wspa.diploma.data.dto.UserDto;
import pl.wspa.diploma.data.services.UserService;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String allUsers(Model model) {

        model.addAttribute("users", userService.findAll());

        return "user/users";
    }

    @GetMapping("users/{id}/user")
    public String user(@PathVariable String id, Model model) {

        model.addAttribute("user", userService.findById(new Long(id)));

        return "user/show";
    }

    @GetMapping("/register")
    public String newUser(Model model) {

        model.addAttribute("user", new UserDto());

        return "user/register";
    }

    @PostMapping("user")
    public String register(@ModelAttribute UserDto userDto) {

        UserDto registeredUser = userService.saveUserDto(userDto);

        return "redirect:/users/" + registeredUser.getId() + "/user";
    }

    @GetMapping("users/{id}/update")
    public String updateUser(@PathVariable String id, Model model) {

        model.addAttribute("user", userService.findById(Long.valueOf(id)));


        return "user/register";
    }

    @GetMapping("/login")
    public String login(Authentication authentication, Model model) {
        model.addAttribute("auth", authentication);
        model.addAttribute("user", userService.getUserBasedOnAuth(authentication));

        return "user/login";
    }


}
