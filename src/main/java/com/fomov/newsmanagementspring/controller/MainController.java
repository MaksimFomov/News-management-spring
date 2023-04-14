package com.fomov.newsmanagementspring.controller;

import com.fomov.newsmanagementspring.model.News;
import com.fomov.newsmanagementspring.model.User;
import com.fomov.newsmanagementspring.service.INewsService;
import com.fomov.newsmanagementspring.service.ServiceException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.transaction.Transactional;
import java.util.List;

@Controller
public class MainController {
    private final INewsService newsService;

    public MainController(INewsService newsService) {
        this.newsService = newsService;
    }

    @Transactional
    @GetMapping("/homePage")
    public String goToHomePage(HttpServletRequest request, Model model) {
        try {
            List<News> latestNews = newsService.getLatestNewsList(5);

            model.addAttribute("news", latestNews);
            model.addAttribute("user", new User());

            return "baseLayout";
        }
        catch (ServiceException e) {
            request.getSession().setAttribute("error_msg", "cannot get the latest list of news");

            return "redirect:/errorPage";
        }
    }

    @Transactional
    @GetMapping("/errorPage")
    public String goToErrorPage(HttpServletRequest request) {
        String errorMessage = (String) request.getSession().getAttribute("error_msg");

        if (errorMessage == null) {
            request.getSession().setAttribute("error_msg", "no such command error");
        }

        return "baseLayout";
    }

    @Transactional
    @GetMapping("/changeLanguage")
    public String changeLanguage() {
        return "baseLayout";
    }

    @Transactional
    @GetMapping("/registration")
    public String goToRegistrationPage(HttpServletRequest request, Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("presentation", "registration");

        return "baseLayout";
    }

    @Transactional
    @GetMapping("/newsList")
    public String goToNewsList(HttpServletRequest request, Model model) {
        try {
            List<News> newsList = newsService.getNewsList();
            if (newsList.size() > 0) {
                model.addAttribute("news", newsList);
            }

            model.addAttribute("presentation", "newsList");

            return "baseLayout";
        } catch (ServiceException e) {
            request.getSession().setAttribute("error_msg", "cannot get the list of news");

            return "redirect:/errorPage";
        }
    }
}
