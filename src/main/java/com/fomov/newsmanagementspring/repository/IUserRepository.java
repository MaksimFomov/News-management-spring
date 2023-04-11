package com.fomov.newsmanagementspring.repository;

import com.fomov.newsmanagementspring.model.User;

public interface IUserRepository {
    boolean authorization(User user) throws RepositoryException;
    boolean registration(User user) throws RepositoryException;
    User findUserByLogin(String login) throws RepositoryException;
}
