package com.fomov.newsmanagementspring.service;

import com.fomov.newsmanagementspring.model.News;

import java.util.List;

public interface INewsService {
    List<News> getNewsList() throws ServiceException;
    List<News> getLatestNewsList(int count) throws ServiceException;
    void addNews(News news) throws ServiceException;
    void updateNews(News news) throws ServiceException;
    void deleteNews(String[] idNews) throws ServiceException;

    News findById(int id) throws ServiceException;
}
