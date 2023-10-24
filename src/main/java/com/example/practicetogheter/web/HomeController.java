package com.example.practicetogheter.web;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    //
    @GetMapping("/hello")
    public String hello() {
        return "/hello";

    }

    @GetMapping("/")
    public String index() {
        return "/index";

    }


    @GetMapping("/goodbye")
    public String goodbye() {
        return "goodbye";

    }



    @GetMapping("/welcome")
    public String welcome() {
        return "welcome";
    }



//    @GetMapping("/")
//    @ResponseBody
//    public String home() {
//        return "home";
//    }
//
//    @GetMapping("/admin")
//    @ResponseBody
//    public String admin() {
//        return "admin";
//    }
}





