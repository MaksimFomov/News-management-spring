package com.fomov.newsmanagementspring.service.impl;

import com.fomov.newsmanagementspring.model.News;
import com.fomov.newsmanagementspring.repository.INewsRepository;
import com.fomov.newsmanagementspring.repository.RepositoryException;
import com.fomov.newsmanagementspring.service.INewsService;
import com.fomov.newsmanagementspring.service.ServiceException;
import com.fomov.newsmanagementspring.validation.INewsValidation;
import com.fomov.newsmanagementspring.validation.impl.NewsValidationImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl implements INewsService {
    private final INewsRepository newsRepository;
    private final INewsValidation newsValidation = new NewsValidationImpl();

    public NewsServiceImpl(INewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    private static final String ERROR_MESSAGE_FOR_INVALID_NEWS_DATA = "fill in all the fields";

    @Override
    public List<News> getNewsList() throws ServiceException {
        try {
            return newsRepository.getNewsList();
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<News> getLatestNewsList(int count) throws ServiceException {
        try {
            return newsRepository.getLatestNewsList(count);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void addNews(News news) throws ServiceException {
        if(!newsValidation.checkNewsData(news.getTitle(), news.getBrief(), news.getContent())) {
            throw new ServiceException(ERROR_MESSAGE_FOR_INVALID_NEWS_DATA);
        }

        try {
            newsRepository.addNews(news);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void updateNews(News news) throws ServiceException {
        if(!newsValidation.checkNewsData(news.getTitle(), news.getBrief(), news.getContent())) {
            throw new ServiceException(ERROR_MESSAGE_FOR_INVALID_NEWS_DATA);
        }

        try {
            newsRepository.updateNews(news);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteNews(String[] idNews) throws ServiceException {
        try {
            newsRepository.deleteNews(idNews);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public News findById(int id) throws ServiceException {
        try {
            return newsRepository.findById(id);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }
}
