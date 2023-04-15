package com.fomov.newsmanagementspring.repository;

import com.fomov.newsmanagementspring.model.News;

import java.util.List;

public interface INewsRepository {
    List<News> getNewsList() throws RepositoryException;
    List<News> getLatestNewsList(int count) throws RepositoryException;
    void addNews(News news) throws RepositoryException;
    void updateNews(News news) throws RepositoryException;
    void deleteNews(String[] idNews) throws RepositoryException;
    News findById(int id) throws RepositoryException;
}
