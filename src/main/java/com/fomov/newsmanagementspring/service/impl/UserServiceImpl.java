package com.fomov.newsmanagementspring.service.impl;

import com.fomov.newsmanagementspring.model.User;
import com.fomov.newsmanagementspring.repository.IUserRepository;
import com.fomov.newsmanagementspring.repository.RepositoryException;
import com.fomov.newsmanagementspring.service.IUserService;
import com.fomov.newsmanagementspring.service.ServiceException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    private final IUserRepository userRepository;

    public UserServiceImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private static final String ROLE_GUEST = "ROLE_GUEST";

    @Override
    @Transactional
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
    @Transactional
    public boolean registration(User user) throws ServiceException {
        try {
            return userRepository.registration(user);
        }
        catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    @Transactional
    public List<User> findUserByLogin(String login) throws ServiceException {
        try {
            return userRepository.findUserByLogin(login);
        }
        catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }
}
