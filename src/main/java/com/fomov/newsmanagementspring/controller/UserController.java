package com.fomov.newsmanagementspring.controller;

import com.fomov.newsmanagementspring.model.User;
import com.fomov.newsmanagementspring.service.IUserService;
import com.fomov.newsmanagementspring.service.ServiceException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String registration(@ModelAttribute("user") User user, HttpServletRequest request) {
        try {
            if (!userService.registration(user)) {
                request.getSession().setAttribute("register_error", "err");
                return "redirect:/registration";
            }
            else {
                request.getSession().setAttribute("register_success", "suc");
                return "redirect:/newsList";
            }
        }
        catch (ServiceException e) {
            request.getSession().setAttribute("invalid_values_for_register", "err");
            return "redirect:/registration";
        }
    }

    @Transactional
    @PostMapping("/authorization")
    public String authorization(@ModelAttribute("user") User user, HttpServletRequest request) {
        try {
            String role = userService.authorization(user);

            if (!role.equals("ROLE_GUEST")) {
                request.getSession().setAttribute("userActivity", "active");
                request.getSession().setAttribute("role", role);

                return "redirect:/newsList";
            }
            else {
                request.getSession().setAttribute("userActivity", "not active");
                request.getSession().setAttribute("auth_error", "wrong login or password");

                return "redirect:/homePage";
            }
        }
        catch (ServiceException e) {
            request.getSession().setAttribute("error_msg", e.getMessage());

            return "redirect:/errorPage";
        }
    }

    @Transactional
    @PostMapping("/signOut")
    public String signOut(HttpServletRequest request) {
        request.getSession().setAttribute("userActivity", "not active");
        request.getSession().setAttribute("role", "guest");

        return "redirect:/homePage";
    }
}
