package com.fomov.newsmanagementspring.controller;

import com.fomov.newsmanagementspring.model.News;
import com.fomov.newsmanagementspring.model.User;
import com.fomov.newsmanagementspring.service.INewsService;
import com.fomov.newsmanagementspring.service.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
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
    public String goToBasePage(Model model) {
        try {
            List<News> news = newsService.getLatestNewsList(5);

            model.addAttribute("news", news);
            model.addAttribute("presentation", "viewNews");
        }
        catch (ServiceException e) {
            model.addAttribute("message", "error");
            return "error";
        }

        return "baseLayout";
    }

    @GetMapping("/changeLanguage")
    public String changeLanguage() {
        return "baseLayout";
    }

    @Transactional
    @GetMapping("/registration")
    public String goToRegistrationPage(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("presentation", "registration");
        return "baseLayout";
    }

}
