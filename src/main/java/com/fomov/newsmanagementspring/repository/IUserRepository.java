package com.fomov.newsmanagementspring.repository;

import com.fomov.newsmanagementspring.model.User;

import java.util.List;

public interface IUserRepository {
    boolean authorization(User user) throws RepositoryException;
    boolean registration(User user) throws RepositoryException;
    List<User> findUserByLogin(String login) throws RepositoryException;
}
