package com.fomov.newsmanagementspring.validation;

public interface IUserValidation {
    boolean checkAuthData(String login, String password);
}
