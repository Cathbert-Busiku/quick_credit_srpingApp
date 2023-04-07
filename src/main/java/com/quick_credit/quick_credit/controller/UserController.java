package com.quick_credit.quick_credit.controller;

import com.quick_credit.quick_credit.dto.UserDto;
import com.quick_credit.quick_credit.entity.User;
import com.quick_credit.quick_credit.repository.UserRepository;
import com.quick_credit.quick_credit.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class UserController {

    private UserService userService;

    private UserRepository userRepository;


    public UserController(UserService userService, UserRepository userRepository) {

        this.userService = userService;
        this.userRepository = userRepository;
    }

    // handler method to handle home page request
    @GetMapping("/index")
    public String home(){
        return "index";
    }

    // handler method to handle user registration form request
    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        // create model object to store form data
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    // handler method to handle user registration form submit request
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto,
                               BindingResult result,
                               Model model){
        User existingUser = userService.findUserByEmail(userDto.getEmail());

        if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }

        if(result.hasErrors()){
            model.addAttribute("user", userDto);
            return "/register";
        }

        userService.saveUser(userDto);
        return "redirect:/register?success";
    }

    // handler method to handle list of users
    @GetMapping("/users")
    public String users(Model model){
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    // handler method to handle login request
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/account")
    public String account(Principal principal , ModelMap modelMap){
        String loggedUser = principal.getName();
        User user = userRepository.findByEmail(loggedUser);
        modelMap.addAttribute("user",user);
        return "clients/dashboard";
    }

//    @GetMapping("/account/savings")
//    public String saving(Principal principal , ModelMap modelMap){
//        String loggedUser = principal.getName();
//        User user = userRepository.findByEmail(loggedUser);
//        String nouser = null;
//
////        if(user.getEmail().isEmpty()) {
////            modelMap.addAttribute("nouser", nouser);
////        }
////        else{
//////            modelMap.addAttribute("user",user);
//////        }
//
//        modelMap.addAttribute("user",user);
//        return "clients/savings";
//    }


}
