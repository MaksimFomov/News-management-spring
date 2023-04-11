package com.fomov.newsmanagementspring.service;

import com.fomov.newsmanagementspring.model.User;
import com.fomov.newsmanagementspring.repository.RepositoryException;

public interface IUserService {
    boolean authorization(User user) throws ServiceException;
    boolean registration(User user) throws ServiceException;
}
