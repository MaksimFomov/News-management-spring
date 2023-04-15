package com.fomov.newsmanagementspring.controller;

import com.fomov.newsmanagementspring.model.News;
import com.fomov.newsmanagementspring.model.User;
import com.fomov.newsmanagementspring.service.INewsService;
import com.fomov.newsmanagementspring.service.ServiceException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@Controller
public class NewsController {
    private final INewsService newsService;

    public NewsController(INewsService newsService) {
        this.newsService = newsService;
    }

    private static final String USER_ID = "userId";

    private static final String INFO_MESSAGE_PARAM_FROM_ADD_AND_EDIT_NEWS = "save_success";
    private static final String INFO_MESSAGE_LOCAL_KEY = "suc";
    private static final String ERROR_MESSAGE_PARAM_FROM_ADD_AND_EDIT_NEWS = "add_or_edit_news_error";
    private static final String ERROR_MESSAGE_LOCAL_KEY = "err";

    private static final String INFO_MESSAGE_PARAM_FROM_DELETE_NEWS = "delete_success";
    private static final String ERROR_MESSAGE_PARAM_FROM_DELETE_NEWS = "error_msg";
    private static final String FIRST_ERROR_MESSAGE_LOCAL_KEY_FROM_DELETE_NEWS = "delete error";
    private static final String SECOND_ERROR_MESSAGE_LOCAL_KEY_FROM_DELETE_NEWS = "no news to delete selected";

    @Transactional
    @PostMapping("/addNews")
    public String addNews(@ModelAttribute("news") News news, HttpServletRequest request) {
        try {
            int userId = (int) (request.getSession().getAttribute(USER_ID));
            User user = new User();
            user.setId(userId);
            news.setUser(user);

            newsService.addNews(news);

            request.getSession().setAttribute(INFO_MESSAGE_PARAM_FROM_ADD_AND_EDIT_NEWS, INFO_MESSAGE_LOCAL_KEY);

            return "redirect:/newsList";
        }
        catch (ServiceException e) {
            request.getSession().setAttribute(ERROR_MESSAGE_PARAM_FROM_ADD_AND_EDIT_NEWS, ERROR_MESSAGE_LOCAL_KEY);

            return "redirect:/addNews";
        }
    }

    @Transactional
    @PostMapping("/deleteNews")
    public String deleteNews(@RequestParam("id") String[] newsIds, HttpServletRequest request) {
        if (newsIds != null) {
            try {
                newsService.deleteNews(newsIds);

                request.getSession().setAttribute(INFO_MESSAGE_PARAM_FROM_DELETE_NEWS, INFO_MESSAGE_LOCAL_KEY);

                return "redirect:/newsList";
            }
            catch (ServiceException e) {
                request.getSession().setAttribute(ERROR_MESSAGE_PARAM_FROM_DELETE_NEWS, FIRST_ERROR_MESSAGE_LOCAL_KEY_FROM_DELETE_NEWS);

                return "redirect:/errorPage";
            }
        }
        else {
            request.getSession().setAttribute(ERROR_MESSAGE_PARAM_FROM_DELETE_NEWS, SECOND_ERROR_MESSAGE_LOCAL_KEY_FROM_DELETE_NEWS);

            return "redirect:/errorPage";
        }
    }

    @Transactional
    @PostMapping("/editNews")
    public String editNews(@ModelAttribute("news") News news, HttpServletRequest request) {
        try {
            int userId = (int) (request.getSession().getAttribute(USER_ID));
            User user = new User();
            user.setId(userId);
            news.setUser(user);

            newsService.updateNews(news);

            request.getSession().setAttribute(INFO_MESSAGE_PARAM_FROM_ADD_AND_EDIT_NEWS, INFO_MESSAGE_LOCAL_KEY);

            return "redirect:/newsList";
        } catch (ServiceException e) {
            request.getSession().setAttribute(ERROR_MESSAGE_PARAM_FROM_ADD_AND_EDIT_NEWS, ERROR_MESSAGE_LOCAL_KEY);

            return "redirect:/errorPage";
        }
    }
}
