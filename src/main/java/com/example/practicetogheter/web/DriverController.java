package com.example.practicetogheter.web;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.practicetogheter.entity.User;
import com.example.practicetogheter.repositories.UserRepository;
import com.example.practicetogheter.services.UserService;

@RequestMapping("/driver")
@Controller
public class DriverController {

    private final UserRepository userRepository;
    private final UserService userService;

    public DriverController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }
    @GetMapping("/add")
    public String showFormUser(Model model) {
        model.addAttribute("users", new User());
        return "driver/addDriver";
    }

    @PostMapping("/add")

    public String perform(@ModelAttribute @Valid User user, String role, BindingResult result) {
        if (result.hasErrors()) {
            return "addDriver";
        }
        userService.saveUser(user, "ROLE_DRIVER");
        return "redirect:/login";

    }
 ////redirectDriver


    @GetMapping("/driverRedirect")
    public String redirectDriver() {
        return "listRides";

    }

}
