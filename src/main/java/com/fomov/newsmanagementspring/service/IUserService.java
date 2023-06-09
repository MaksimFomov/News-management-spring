package com.fomov.newsmanagementspring.service;

import com.fomov.newsmanagementspring.model.User;
import com.fomov.newsmanagementspring.repository.RepositoryException;

import java.util.List;

public interface IUserService {
    String authorization(User user) throws ServiceException;
    boolean registration(User user) throws ServiceException;
    List<User> findUserByLogin(String login) throws ServiceException;
}
