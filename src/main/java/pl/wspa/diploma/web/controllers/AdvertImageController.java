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
import pl.wspa.diploma.data.dto.AdvertDto;
import pl.wspa.diploma.data.services.AdvertImageService;
import pl.wspa.diploma.data.services.AdvertService;
import pl.wspa.diploma.data.services.UserService;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class AdvertImageController {

    private final AdvertService advertService;
    private final AdvertImageService advertImageService;
    private final UserService userService;

    public AdvertImageController(AdvertService advertService, AdvertImageService advertImageService, UserService userService) {
        this.advertService = advertService;
        this.advertImageService = advertImageService;
        this.userService = userService;
    }

    @GetMapping("adverts/{id}/advert/image")
    public String advertImageUploadForm(@PathVariable String id, Model model, Authentication authentication) {

        model.addAttribute("advert", advertService.findAdvertDtoById(Long.valueOf(id)));
        model.addAttribute("authentication", authentication);
        model.addAttribute("user", userService.getUserBasedOnAuth(authentication));
        model.addAttribute("user", userService.getUserBasedOnAuth(authentication));
        model.addAttribute("isUser", userService.isUser(authentication));
        model.addAttribute("isAdmin", userService.isAdmin(authentication));

        return "/advert/image";
    }

    @PostMapping("adverts/{id}/advert/image")
    public String handleImagePost(@PathVariable String id, @RequestParam("imagefile") MultipartFile image) {

        advertImageService.saveAdvertImageFile(Long.valueOf(id), image);

        return "redirect:/adverts/" + id + "/show";
    }

    @GetMapping("adverts/{id}/advert/advertimage")
    public void renderAdvertImage(@PathVariable String id, HttpServletResponse response) throws IOException {
        AdvertDto advertDto = advertService.findAdvertDtoById(Long.valueOf(id));

        if (advertDto.getImage() != null) {
            byte[] byteArray = new byte[advertDto.getImage().length];

            int i = 0;

            for (Byte wrapedByte : advertDto.getImage()) {
                byteArray[i++] = wrapedByte;
            }

            response.setContentType("image/jpeg");
            InputStream is = new ByteArrayInputStream(byteArray);
            IOUtils.copy(is, response.getOutputStream());
        }
    }
}
