package com.example.practicetogheter.web;

import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.example.practicetogheter.entity.Ride;
import com.example.practicetogheter.entity.User;
import com.example.practicetogheter.repositories.RideRepository;
import com.example.practicetogheter.services.CurrentUser;

import java.util.Iterator;
import java.util.List;


@Controller
@RequestMapping("/ride")
public class RideController {


    private final RideRepository rideRepository;

    public RideController(RideRepository rideRepository) {
        this.rideRepository = rideRepository;
    }


    //AddRide
    @GetMapping("/add")
    public String showFormUser(Model model) {
        model.addAttribute("ride", new Ride());
        return "ride/addRide";
    }

    @PostMapping("/add")

    public String perform(@AuthenticationPrincipal CurrentUser customUser, @ModelAttribute @Valid Ride ride, BindingResult result) {
        if (result.hasErrors()) {
            return "ride/addRide";
        }
        User entityUser = customUser.getUser();
        ride.setUser(entityUser);
        rideRepository.save(ride);
        return "redirect:/ride/showRides";

    }

    //CurrentUserRides

    @GetMapping("/showRides")

    public String show(@AuthenticationPrincipal CurrentUser customUser, Model model) {
        User entityUser = customUser.getUser();
        long entityUserId = entityUser.getId();
        model.addAttribute("rides", rideRepository.findAllRidesByUserId(entityUserId));
        return "ride/showRide";

    }
//showOne

    @GetMapping("/showOne/{id}")

    public String showOne(Model model, @PathVariable Long id) {
        model.addAttribute("rides", rideRepository.findOne(id));
        return "ride/showOne";

    }

    //deleteRide
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable long id) {

        rideRepository.delete(rideRepository.findOne(id));
        return "redirect:/welcome";
    }

    //updateRide

    @GetMapping("/update/{id}")
    public String showForm(Model model, @PathVariable long id) {
        Ride r = rideRepository.findOne(id);
        model.addAttribute("ride", r);
        return "ride/updateRide";
    }

    @PostMapping("/update")
    public String performUpdate(@ModelAttribute Ride ride) {
        rideRepository.save(ride);
        return "redirect:/ride/showRides";

    }


    //Show all rides
    @GetMapping("/listRides")
    public String toString(Model model) {
        model.addAttribute("rides", rideRepository.findAll());
        return "ride/listRides";
    }

    @GetMapping("/reserveRide/{id}")
    public String reserveRide(@AuthenticationPrincipal CurrentUser customUser, Model model, @PathVariable long id) {
        User entityUser = customUser.getUser();
        long entityUserId = entityUser.getId();
        Ride r = rideRepository.findOne(id);
        int seats = r.getFreeSeats();
        List<User> ridePassengers = r.getUsers();
        String reply = "";
        if (seats > 0) {
            int newSeats = seats -1;
            r.setFreeSeats(newSeats);
            ridePassengers.add(entityUser);
            r.setUsers(ridePassengers);
            rideRepository.save(r);
            model.addAttribute("ride", r);
            reply = "redirect:/ride/success/?id=" + id;
        } else {
            reply = "redirect:/ride/noSeats";
        }
        return reply;
    }
//Cancell

    @GetMapping("/cancell/{id}")
    public String cancellRide(@AuthenticationPrincipal CurrentUser customUser, Model model, @PathVariable long id) {
        User entityUser = customUser.getUser();
        long entityUserId = entityUser.getId();
        Ride r = rideRepository.findOne(id);
        int seats = r.getFreeSeats();
        r.setFreeSeats(seats + 1);
        List<User> ridePassengers = r.getUsers();
        Iterator<User> it = ridePassengers.iterator();
        while (it.hasNext()) {
            if (entityUser == it.next()) {
                it.remove();
            }

        }rideRepository.save(r);
        return "/ride/cancell";

    }






    //success

    @GetMapping("/success/")
    public String succes(@RequestParam long id, Model model) {
        Ride r = rideRepository.findOne(id);
        model.addAttribute("ride", r);
        return "success";

    }

    //noSeats
    @GetMapping("/noSeats")
    public String noSeats() {
        return "noSeats";

    }


//findByDestinationPlace

    @GetMapping("/findByDestination")
    public String findByDestination( @RequestParam String placea, Model model) {
        List<Ride> rides = rideRepository.findbyDestination(placea);
        model.addAttribute("rides", rides);
        return "ride/viewRidesbySearch";
    }


    //byStart

     @GetMapping("/byStart")

    public String findByStart( @RequestParam String placeb, Model model) {
       List<Ride> rides = rideRepository.findbyStart(placeb);
       model.addAttribute("rides", rides);
        return "ride/viewRidesbySearch";
    }

    @GetMapping("/byDate")

    public String findByDate( @RequestParam String date, Model model) {
        List<Ride> rides = rideRepository.findbyDate(date);
        model.addAttribute("rides", rides);
        return "ride/viewRidesbySearch";
    }





}

//    //updateRide
//    @PostMapping("/deleteRide/{id}")
//    public String performDelete(@ModelAttribute Ride ride, @PathVariable long id) {
//        rideRepository.delete(ride);
//        return "redirect:/hello";
//    }




