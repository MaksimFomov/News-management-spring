package com.fomov.newsmanagementspring.service.impl;

import com.fomov.newsmanagementspring.model.News;
import com.fomov.newsmanagementspring.repository.INewsRepository;
import com.fomov.newsmanagementspring.repository.RepositoryException;
import com.fomov.newsmanagementspring.service.INewsService;
import com.fomov.newsmanagementspring.service.ServiceException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class NewsServiceImpl implements INewsService {
    private final INewsRepository newsRepository;

    public NewsServiceImpl(INewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Override
    @Transactional
    public List<News> getNewsList() throws ServiceException {
        try {
            return newsRepository.getNewsList();
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    @Transactional
    public List<News> getLatestNewsList(int count) throws ServiceException {
        try {
            return newsRepository.getLatestNewsList(count);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    @Transactional
    public void addNews(News news) throws ServiceException {
        try {
            newsRepository.addNews(news);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    @Transactional
    public void updateNews(News news) throws ServiceException {
        try {
            newsRepository.updateNews(news);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    @Transactional
    public void deleteNews(String[] idNews) throws ServiceException {
        try {
            newsRepository.deleteNews(idNews);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }
}
