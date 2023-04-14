package com.fomov.newsmanagementspring.repository.impl;

import com.fomov.newsmanagementspring.model.Role;
import com.fomov.newsmanagementspring.model.User;
import com.fomov.newsmanagementspring.repository.IUserRepository;
import com.fomov.newsmanagementspring.repository.RepositoryException;
import org.hibernate.*;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements IUserRepository {
    private final SessionFactory sessionFactory;

    public UserRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private static final String HQL_QUERY_FIND_USER_BY_LOGIN = "FROM User WHERE login=:login";

    @Override
    public boolean authorization(User user) throws RepositoryException {
        String password;

        try {
            if(!findUserByLogin(user.getLogin()).isEmpty()) {
                password = findUserByLogin(user.getLogin()).get(0).getPassword();

                if(user.getPassword().equals(password)) {
                    return true;
                }
            }
        }
        catch (HibernateException e) {
            throw new RepositoryException(e);
        }

        return false;
    }

    @Override
    public boolean registration(User user) throws RepositoryException {
        try {
            if(findUserByLogin(user.getLogin()).isEmpty()) {
                Session session = sessionFactory.getCurrentSession();

                Role role = session.get(Role.class, 1);
                user.setRole(role);

                session.saveOrUpdate(user);

                return true;
            }
        }
        catch (HibernateException e) {
            throw new RepositoryException(e);
        }

        return false;
    }

    @Override
    public String getRole(String login) throws RepositoryException {
        try {
            return findUserByLogin(login).get(0).getRole().getTitle();
        }
        catch (HibernateException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public List<User> findUserByLogin(String login) throws RepositoryException {
        Session session = sessionFactory.getCurrentSession();

        try {
            Query query = session.createQuery(HQL_QUERY_FIND_USER_BY_LOGIN);
            query.setParameter("login", login);

            return query.list();
        }
        catch (HibernateException e) {
            throw new RepositoryException(e);
        }
    }
}
