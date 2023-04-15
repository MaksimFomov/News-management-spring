package com.fomov.newsmanagementspring.controller;

import com.fomov.newsmanagementspring.model.User;
import com.fomov.newsmanagementspring.service.IUserService;
import com.fomov.newsmanagementspring.service.ServiceException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@Controller
public class UserController {
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    private static final String ROLE_GUEST = "ROLE_GUEST";
    private static final String USER_ID = "userId";

    private static final String ROLE_PARAM = "role";
    private static final String USER_ACTIVITY_PARAM = "userActivity";
    private static final String USER_ACTIVITY_ACTIVE_LOCAL_KEY = "active";
    private static final String USER_ACTIVITY_NOT_ACTIVE_LOCAL_KEY = "not active";

    private static final String ERROR_MESSAGE_PARAM_FOR_REGISTRATION = "register_error";
    private static final String ERROR_MESSAGE_LOCAL_KEY_FOR_REGISTRATION = "err";
    private static final String SUCCESS_MESSAGE_PARAM_FOR_REGISTRATION = "register_success";
    private static final String SUCCESS_MESSAGE_LOCAL_KEY_FOR_REGISTRATION = "suc";
    private static final String ERROR_MESSAGE_PARAM_FOR_REGISTRATION_EXCEPTION = "invalid_values_for_register";
    private static final String AUTH_ERROR_MESSAGE_PARAM = "auth_error";
    private static final String AUTH_ERROR_MESSAGE_LOCAL_KEY = "wrong login or password";
    private static final String ERROR_MESSAGE_PARAM = "error_msg";

    @Transactional
    @PostMapping("/registration")
    public String registration(@ModelAttribute("user") User user, HttpServletRequest request) {
        try {
            if (!userService.registration(user)) {
                request.getSession().setAttribute(ERROR_MESSAGE_PARAM_FOR_REGISTRATION, ERROR_MESSAGE_LOCAL_KEY_FOR_REGISTRATION);
                return "redirect:/registration";
            }
            else {
                request.getSession().setAttribute(SUCCESS_MESSAGE_PARAM_FOR_REGISTRATION, SUCCESS_MESSAGE_LOCAL_KEY_FOR_REGISTRATION);
                return "redirect:/newsList";
            }
        }
        catch (ServiceException e) {
            request.getSession().setAttribute(ERROR_MESSAGE_PARAM_FOR_REGISTRATION_EXCEPTION, ERROR_MESSAGE_LOCAL_KEY_FOR_REGISTRATION);
            return "redirect:/registration";
        }
    }

    @Transactional
    @PostMapping("/authorization")
    public String authorization(@ModelAttribute("user") User user, HttpServletRequest request) {
        try {
            String role = userService.authorization(user);

            if (!role.equals(ROLE_GUEST)) {
                request.getSession().setAttribute(USER_ACTIVITY_PARAM, USER_ACTIVITY_ACTIVE_LOCAL_KEY);
                request.getSession().setAttribute(ROLE_PARAM, role);
                request.getSession().setAttribute(USER_ID, userService.findUserByLogin(user.getLogin()).get(0).getId());

                return "redirect:/newsList";
            }
            else {
                request.getSession().setAttribute(USER_ACTIVITY_PARAM, USER_ACTIVITY_NOT_ACTIVE_LOCAL_KEY);
                request.getSession().setAttribute(AUTH_ERROR_MESSAGE_PARAM, AUTH_ERROR_MESSAGE_LOCAL_KEY);

                return "redirect:/homePage";
            }
        }
        catch (ServiceException e) {
            request.getSession().setAttribute(ERROR_MESSAGE_PARAM, e.getMessage());

            return "redirect:/errorPage";
        }
    }

    @Transactional
    @PostMapping("/signOut")
    public String signOut(HttpServletRequest request) {
        request.getSession().setAttribute(USER_ACTIVITY_PARAM, USER_ACTIVITY_NOT_ACTIVE_LOCAL_KEY);
        request.getSession().setAttribute(ROLE_PARAM, ROLE_GUEST);

        return "redirect:/homePage";
    }
}
