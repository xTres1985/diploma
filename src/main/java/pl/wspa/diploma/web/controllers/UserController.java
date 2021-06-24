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
    public String allUsers(Model model, Authentication authentication) {

        model.addAttribute("users", userService.findAll());
        model.addAttribute("authentication", authentication);
        model.addAttribute("user", userService.getUserBasedOnAuth(authentication));
        model.addAttribute("isUser", userService.isUser(authentication));
        model.addAttribute("isAdmin", userService.isAdmin(authentication));

        return "user/users";
    }

    @GetMapping("users/{id}/user")
    public String user(@PathVariable String id, Model model, Authentication authentication) {

        model.addAttribute("founduser", userService.findById(new Long(id)));
        model.addAttribute("authentication", authentication);
        model.addAttribute("user", userService.getUserBasedOnAuth(authentication));
        model.addAttribute("isUser", userService.isUser(authentication));
        model.addAttribute("isAdmin", userService.isAdmin(authentication));

        return "user/show";
    }

    @GetMapping("/register")
    public String newUser(Model model, Authentication authentication) {

        model.addAttribute("userDto", new UserDto());
        model.addAttribute("authentication", authentication);
        model.addAttribute("user", userService.getUserBasedOnAuth(authentication));
        model.addAttribute("isUser", userService.isUser(authentication));
        model.addAttribute("isAdmin", userService.isAdmin(authentication));

        return "user/register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UserDto userDto) {

        UserDto registeredUser = userService.saveUserDto(userDto);

        return "redirect:/";
    }

    @GetMapping("users/{id}/update")
    public String updateUser(@PathVariable String id, Model model, Authentication authentication) {

        model.addAttribute("userDto", userService.findById(Long.valueOf(id)));
        model.addAttribute("authentication", authentication);
        model.addAttribute("user", userService.getUserBasedOnAuth(authentication));
        model.addAttribute("isUser", userService.isUser(authentication));
        model.addAttribute("isAdmin", userService.isAdmin(authentication));

        return "user/update";
    }

    @GetMapping("/login")
    public String login(Authentication authentication, Model model) {
        model.addAttribute("auth", authentication);
        model.addAttribute("user", userService.getUserBasedOnAuth(authentication));
        model.addAttribute("isUser", userService.isUser(authentication));
        model.addAttribute("isAdmin", userService.isAdmin(authentication));

        return "user/login";
    }

    @GetMapping("/users/{id}/delete")
    public String deleteUserById(@PathVariable String id) {

        userService.deleteById(Long.valueOf(id));

        return "redirect:/users";
    }

}
