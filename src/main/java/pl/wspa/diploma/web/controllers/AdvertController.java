package pl.wspa.diploma.web.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.wspa.diploma.data.dao.enums.AdvertType;
import pl.wspa.diploma.data.dto.AdvertDto;
import pl.wspa.diploma.data.services.AdvertService;
import pl.wspa.diploma.data.services.UserService;

@Controller
public class AdvertController {


    private final AdvertService advertService;
    private final UserService userService;

    public AdvertController(AdvertService advertService, UserService userService) {
        this.advertService = advertService;
        this.userService = userService;
    }

    @GetMapping({"/adverts", "/", ""})
    public String allAdverts(Model model, Authentication authentication) {

        model.addAttribute("adverts", advertService.findAll());
        model.addAttribute("authentication", authentication);
        model.addAttribute("user", userService.getUserBasedOnAuth(authentication));

        return "advert/adverts";
    }

    @GetMapping("/adverts/{id}/show")
    public String advert(@PathVariable String id, Model model, Authentication authentication) {
        AdvertDto advertDto = advertService.findAdvertDtoById(new Long(id));

        model.addAttribute("advert", advertService.findAdvertDtoById(new Long(id)));
        model.addAttribute("authentication", authentication);
        model.addAttribute("user", userService.getUserBasedOnAuth(authentication));
//        model.addAttribute("user", userService.findUserDtoById(advertService.findAdvertDtoById(new Long(id)).getUserDto().getId()));


        return "advert/show";
    }

    @GetMapping("adverts/advert/new")
    public String newAdvert(Model model, Authentication authentication) {

        model.addAttribute("advert", new AdvertDto());
        model.addAttribute("advertType", AdvertType.values());
        model.addAttribute("authentication", authentication);
        model.addAttribute("user", userService.getUserBasedOnAuth(authentication));

        return "advert/advertform";
    }

    @PostMapping("advert")
    public String saveAdvert(@ModelAttribute AdvertDto advertDto, Authentication authentication) {

        advertDto.setUserDto(userService.getUserBasedOnAuth(authentication));

        AdvertDto savedDto = advertService.saveAdvertDto(advertDto);

        return "redirect:/adverts/" + savedDto.getId() + "/show";
    }

    @GetMapping("adverts/{id}/update")
    public String updateAdvert(@PathVariable String id, Model model, Authentication authentication) {

        model.addAttribute("advert", advertService.findAdvertDtoById(Long.valueOf(id)));
        model.addAttribute("advertType", AdvertType.values());
        model.addAttribute("authentication", authentication);
        model.addAttribute("user", userService.getUserBasedOnAuth(authentication));

        return "advert/advertform";

    }

    @GetMapping("adverts/{id}/delete")
    public String deleteById(@PathVariable String id) {

        advertService.deleteById(Long.valueOf(id));
        return "redirect:/adverts";
    }


}
