package com.fomov.newsmanagementspring.validation.impl;

import com.fomov.newsmanagementspring.validation.IUserValidation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidationImpl implements IUserValidation {
    private static final String PATTERN_FOR_LOGIN = "^[a-zA-Z][a-zA-Z0-9-_\\.]{1,20}$";
    private static final String PATTERN_FOR_PASSWORD = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).*$";

    private static final Pattern LOGIN_PATTERN = Pattern.compile(PATTERN_FOR_LOGIN);
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(PATTERN_FOR_PASSWORD);

    @Override
    public boolean checkAuthData(String login, String password) {
        Matcher loginMatcher = LOGIN_PATTERN.matcher(login);
        Matcher passwordMatcher = PASSWORD_PATTERN.matcher(password);

        if(loginMatcher.matches() && passwordMatcher.matches()) {
            return true;
        }

        return false;
    }
}
