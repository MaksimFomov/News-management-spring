package com.fomov.newsmanagementspring.controller;

import com.fomov.newsmanagementspring.model.User;
import com.fomov.newsmanagementspring.service.IUserService;
import com.fomov.newsmanagementspring.service.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@Controller
public class UserController {
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @Transactional
    @PostMapping("/registration")
    public String registration(@ModelAttribute("user") User user, Model model) {
        try {
            if (!userService.registration(user)) {
                model.addAttribute("register_error", "err");
                return "redirect:/registration";
            }
            else {
                model.addAttribute("register_success", "suc");
                return "baseLayout";
            }
        }
        catch (ServiceException e) {
            model.addAttribute("invalid_values_for_register", "err");
            return "redirect:/registration";
        }
    }

    @PostMapping("/homePage")
    public String authorization(@ModelAttribute("user") User user, Model model) {
        try {
            if (!userService.authorization(user)) {
                model.addAttribute("login_error", "err");
                return "redirect:/baseLayout";
            }
            else {
                model.addAttribute("login_success", "suc");
                return "error";
            }
        }
        catch (ServiceException e) {
            model.addAttribute("invalid_values_for_register", "err");
            return "redirect:/baseLayout";
        }
    }
}
