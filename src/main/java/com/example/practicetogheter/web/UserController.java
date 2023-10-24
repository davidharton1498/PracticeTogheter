package com.example.practicetogheter.web;

import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.example.practicetogheter.entity.User;
import com.example.practicetogheter.repositories.UserRepository;
import com.example.practicetogheter.services.CurrentUser;
import com.example.practicetogheter.services.UserService;


@Controller
@RequestMapping("/user")

public class UserController {


    private final UserRepository userRepository;
    private final UserService userService;

    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }


    //add
    @GetMapping("/add")
    public String showFormUser(Model model) {
        model.addAttribute("users", new User());
        return "user/addUser";
    }

    @PostMapping("/add")

    public String perform(@ModelAttribute @Valid User user, String role, BindingResult result) {
        if (result.hasErrors()) {
            return "addUser";
        }
        userService.saveUser(user, "ROLE_USER");
        return "redirect:/login";

    }

    @GetMapping("/showUser")

    public String showUser(@AuthenticationPrincipal CurrentUser customUser, Model model) {
        User entityUser = customUser.getUser();
        long entityUserId = entityUser.getId();
        model.addAttribute("user", userRepository.findOne(entityUserId));
        return "user/showUser";

    }

    //updateUser
    @GetMapping("/update/{id}")
    public String showFormUser(Model model, @PathVariable long id) {
        model.addAttribute("user", userRepository.getOne(id));
        return "user/updateUser";
    }

    @PostMapping("/update")
    public String performUpdate(@ModelAttribute User user) {
        userRepository.save(user);

        return "redirect:/showUser";
    }





    //deleteUser
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable long id) {

        userRepository.delete(userRepository.findOne(id));
        return "redirect:/welcome";
    }


//    //redirectuser
//
//    @GetMapping("/userRedirect")
//    public String redirectUser() {
//        return "/login";
//
//    }


    //show User

    //ShowbyId
    @GetMapping("/showU/{id}")

    public String show(Model model, @PathVariable long id) {
        User user = userRepository.getOne(id);
        model.addAttribute("user", user);
        return "user/showUser";
    }

}


