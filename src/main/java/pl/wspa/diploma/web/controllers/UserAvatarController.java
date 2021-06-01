package pl.wspa.diploma.web.controllers;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pl.wspa.diploma.data.dto.UserDto;
import pl.wspa.diploma.data.services.AvatarImageService;
import pl.wspa.diploma.data.services.UserService;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class UserAvatarController {

    private final UserService userService;
    private final AvatarImageService avatarImageService;


    public UserAvatarController(UserService userService, AvatarImageService avatarImageService) {
        this.userService = userService;
        this.avatarImageService = avatarImageService;
    }

    @GetMapping("users/{id}/user/image")
    public String userAvatarUploadForm(@PathVariable String id, Model model, Authentication authentication) {

        model.addAttribute("user", userService.findUserDtoById(Long.valueOf(id)));
        model.addAttribute("authentication", authentication);
        model.addAttribute("user", userService.getUserBasedOnAuth(authentication));
        model.addAttribute("isUser", userService.isUser(authentication));
        model.addAttribute("isAdmin", userService.isAdmin(authentication));

        return "/user/avatar";
    }

    @PostMapping("users/{id}/user/image")
    public String handleAvatarPost(@PathVariable String id, @RequestParam("imagefile") MultipartFile avatar) {

        avatarImageService.saveAvatarImageFile(Long.valueOf(id), avatar);

        return "redirect:/users/" + id + "/user";
    }

    @GetMapping("users/{id}/user/useravatar")
    public void renderAvatarFile(@PathVariable String id, HttpServletResponse response) throws IOException {
        UserDto userDto = userService.findUserDtoById(Long.valueOf(id));

        if (userDto.getAvatarImage() != null) {
            byte[] byteArray = new byte[userDto.getAvatarImage().length];

            int i = 0;

            for (Byte wrapedByte : userDto.getAvatarImage()) {
                byteArray[i++] = wrapedByte;
            }

            response.setContentType("image/jpeg");
            InputStream is = new ByteArrayInputStream(byteArray);
            IOUtils.copy(is, response.getOutputStream());
        }
    }
}
