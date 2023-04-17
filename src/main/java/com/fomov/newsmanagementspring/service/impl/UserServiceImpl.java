package com.fomov.newsmanagementspring.service.impl;

import com.fomov.newsmanagementspring.model.User;
import com.fomov.newsmanagementspring.repository.IUserRepository;
import com.fomov.newsmanagementspring.repository.RepositoryException;
import com.fomov.newsmanagementspring.service.IUserService;
import com.fomov.newsmanagementspring.service.ServiceException;
import com.fomov.newsmanagementspring.validation.IUserValidation;
import com.fomov.newsmanagementspring.validation.impl.UserValidationImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    private final IUserRepository userRepository;
    private final IUserValidation userValidation = new UserValidationImpl();

    public UserServiceImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private static final String ERROR_MESSAGE_FOR_INVALID_LOGIN_OR_PASSWORD = "invalid login or password value";

    private static final String ROLE_GUEST = "ROLE_GUEST";

    @Override
    public String authorization(User user) throws ServiceException {
        try {
            if(userRepository.authorization(user)) {
                return userRepository.getRole(user.getLogin());
            }
        }
        catch (RepositoryException e) {
            throw new ServiceException(e);
        }

        return ROLE_GUEST;
    }

    @Override
    public boolean registration(User user) throws ServiceException {
        if(!userValidation.checkAuthData(user.getLogin(), user.getPassword())) {
            throw new ServiceException(ERROR_MESSAGE_FOR_INVALID_LOGIN_OR_PASSWORD);
        }

        try {
            return userRepository.registration(user);
        }
        catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> findUserByLogin(String login) throws ServiceException {
        try {
            return userRepository.findUserByLogin(login);
        }
        catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }
}
