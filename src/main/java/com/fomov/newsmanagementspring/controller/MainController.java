package com.fomov.newsmanagementspring.controller;

import com.fomov.newsmanagementspring.model.News;
import com.fomov.newsmanagementspring.model.User;
import com.fomov.newsmanagementspring.service.INewsService;
import com.fomov.newsmanagementspring.service.ServiceException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.util.List;

@Controller
public class MainController {
    private final INewsService newsService;

    public MainController(INewsService newsService) {
        this.newsService = newsService;
    }

    private static final String NEWS_PARAM = "news";
    private static final String USER_PARAM = "user";
    private static final String LOCAL_PARAM = "local";
    private static final String USER_ACTIVITY_ACTIVE = "active";
    private static final String USER_ACTIVITY = "userActivity";

    private static final String PRESENTATION_PARAM = "presentation";
    private static final String PRESENTATION_LOCAL_KEY_FOR_REGISTRATION = "registration";
    private static final String PRESENTATION_LOCAL_KEY_FOR_NEWS_LIST = "newsList";
    private static final String PRESENTATION_LOCAL_KEY_FOR_ADD_NEWS = "addNews";
    private static final String PRESENTATION_LOCAL_KEY_FOR_EDIT_NEWS = "editNews";
    private static final String PRESENTATION_LOCAL_KEY_FOR_VIEW_NEWS = "viewNews";

    private static final String ERROR_MESSAGE_PARAM = "error_msg";
    private static final String ERROR_MESSAGE_LOCAL_KEY_FOR_HOME_PAGE = "cannot get the latest list of news";
    private static final String ERROR_MESSAGE_LOCAL_KEY_FOR_ERROR_PAGE = "no such command error";
    private static final String ERROR_MESSAGE_LOCAL_KEY_FOR_NEWS_LIST = "cannot get the list of news";
    private static final String ERROR_MESSAGE_LOCAL_KEY_FOR_EDIT_AND_VIEW_NEWS = "cannot find the news by id";

    @Transactional
    @GetMapping("/homePage")
    public String goToHomePage(HttpServletRequest request, Model model) {
        try {
            List<News> latestNews = newsService.getLatestNewsList(5);

            model.addAttribute(NEWS_PARAM, latestNews);
            model.addAttribute(USER_PARAM, new User());

            return "baseLayout";
        }
        catch (ServiceException e) {
            request.getSession().setAttribute(ERROR_MESSAGE_PARAM, ERROR_MESSAGE_LOCAL_KEY_FOR_HOME_PAGE);

            return "redirect:/errorPage";
        }
    }

    @Transactional
    @GetMapping("/errorPage")
    public String goToErrorPage(HttpServletRequest request) {
        String errorMessage = (String) request.getSession().getAttribute(ERROR_MESSAGE_PARAM);

        if (errorMessage == null) {
            request.getSession().setAttribute(ERROR_MESSAGE_PARAM, ERROR_MESSAGE_LOCAL_KEY_FOR_ERROR_PAGE);
        }

        return "baseLayout";
    }

    @Transactional
    @GetMapping("/changeLanguage")
    public String changeLanguage(@RequestParam("local") String local, HttpServletRequest request) {
        request.getSession().setAttribute(LOCAL_PARAM, local);

        return USER_ACTIVITY_ACTIVE.equals(request.getSession().getAttribute(USER_ACTIVITY))
                ? "redirect:/newsList"
                : "redirect:/homePage";
    }

    @Transactional
    @GetMapping("/registration")
    public String goToRegistrationPage(Model model) {
        model.addAttribute(USER_PARAM, new User());
        model.addAttribute(PRESENTATION_PARAM, PRESENTATION_LOCAL_KEY_FOR_REGISTRATION);

        return "baseLayout";
    }

    @Transactional
    @GetMapping("/newsList")
    public String goToNewsListPage(HttpServletRequest request, Model model) {
        try {
            List<News> newsList = newsService.getNewsList();
            if (newsList.size() > 0) {
                model.addAttribute(NEWS_PARAM, newsList);
            }

            model.addAttribute(PRESENTATION_PARAM, PRESENTATION_LOCAL_KEY_FOR_NEWS_LIST);

            return "baseLayout";
        } catch (ServiceException e) {
            request.getSession().setAttribute(ERROR_MESSAGE_PARAM, ERROR_MESSAGE_LOCAL_KEY_FOR_NEWS_LIST);

            return "redirect:/errorPage";
        }
    }

    @Transactional
    @GetMapping("/addNews")
    public String goToAddNewsPage(Model model) {
        model.addAttribute(NEWS_PARAM, new News());
        model.addAttribute(PRESENTATION_PARAM, PRESENTATION_LOCAL_KEY_FOR_ADD_NEWS);

        return "baseLayout";
    }

    @Transactional
    @GetMapping("/editNews")
    public String goToEditNewsPage(@RequestParam("id") int id, HttpServletRequest request, Model model) {
        try {
            News news = newsService.findById(id);
            model.addAttribute(NEWS_PARAM, news);
            model.addAttribute(PRESENTATION_PARAM, PRESENTATION_LOCAL_KEY_FOR_EDIT_NEWS);

            return "baseLayout";
        }
        catch (ServiceException e) {
            request.getSession().setAttribute(ERROR_MESSAGE_PARAM, ERROR_MESSAGE_LOCAL_KEY_FOR_EDIT_AND_VIEW_NEWS);

            return "redirect:/errorPage";
        }
    }

    @Transactional
    @GetMapping("/viewNews")
    public String goToViewNewsPage(@RequestParam("id") int id, HttpServletRequest request, Model model) {
        try {
            News news  = newsService.findById(id);
            model.addAttribute(NEWS_PARAM, news);
            model.addAttribute(PRESENTATION_PARAM, PRESENTATION_LOCAL_KEY_FOR_VIEW_NEWS);

            return "baseLayout";
        } catch (ServiceException e) {
            request.getSession().setAttribute(ERROR_MESSAGE_PARAM, ERROR_MESSAGE_LOCAL_KEY_FOR_EDIT_AND_VIEW_NEWS);

            return "redirect:/errorPage";
        }
    }
}
