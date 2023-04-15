package com.fomov.newsmanagementspring.repository.impl;

import com.fomov.newsmanagementspring.model.News;
import com.fomov.newsmanagementspring.repository.INewsRepository;
import com.fomov.newsmanagementspring.repository.RepositoryException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Repository
public class NewsRepositoryImpl implements INewsRepository {
    private final SessionFactory sessionFactory;

    public NewsRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private static final String HQL_QUERY_FOR_GET_NEWS_LIST = "FROM News";
    private static final String HQL_QUERY_FOR_DELETE_NEWS = "DELETE FROM News WHERE id=:id";

    private static final String QUERY_PARAMETER_ID = "id";

    @Override
    public List<News> getNewsList() throws RepositoryException {
        Session session = sessionFactory.getCurrentSession();
        List<News> newsList;

        try {
            Query query = session.createQuery(HQL_QUERY_FOR_GET_NEWS_LIST);
            newsList = query.list();

            Collections.sort(newsList, (news1, news2) -> news2.getDate().compareTo(news1.getDate()));

            return newsList;
        }
        catch (HibernateException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public List<News> getLatestNewsList(int count) throws RepositoryException {
        if(getNewsList().size() > count) {
            return getNewsList().stream().limit(count).toList();
        }

        return getNewsList();
    }

    @Override
    public void addNews(News news) throws RepositoryException {
        Session session = sessionFactory.getCurrentSession();

        try {
            session.saveOrUpdate(news);
        }
        catch (HibernateException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public void updateNews(News news) throws RepositoryException {
        Session session = sessionFactory.getCurrentSession();

        try {
            session.saveOrUpdate(news);
        }
        catch (HibernateException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public void deleteNews(String[] idNews) throws RepositoryException {
        Session session = sessionFactory.getCurrentSession();

        try {
            Query query = session.createQuery(HQL_QUERY_FOR_DELETE_NEWS);

            for(String id: idNews) {
                query.setParameter(QUERY_PARAMETER_ID, Integer.parseInt(id));
                query.executeUpdate();
            }
        }
        catch (HibernateException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public News findById(int id) throws RepositoryException {
        return getNewsList().stream().filter(o -> Objects.equals(o.getId(), id)).findAny().orElse(null);
    }
}
