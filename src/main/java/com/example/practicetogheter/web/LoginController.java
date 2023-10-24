package com.example.practicetogheter.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.example.practicetogheter.services.UserService;

@Controller
public class LoginController {


    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String login() {
        return "login";

    }

    @RequestMapping(value = {"/403"}, method = RequestMethod.GET)
    public String error() {
        return "/403";

    }

    @RequestMapping("/eror")
    public String render404(Model model) {
        // Add model attributes
        return "error";
    }

//
//    @GetMapping("/login")
//    public String showFormUser(Model model) {
//        model.addAttribute("user", new User());
//        return "login";
//    }

//    @GetMapping("/login-redirect")
//
//    public String admin2(@AuthenticationPrincipal CurrentUser customUser) {
//        String answer = "";
//
//        User entityUser = customUser.getUser();
//        if (entityUser.getRoles().contains("USER")) {
//            answer = "redirect:/userRedirect";
//        } else if (entityUser.getRoles().contains("DRIVER")) {
//            answer = "redirect:/driverRedirect";
//        }
//        return answer;
//    }
}

