package com.fomov.newsmanagementspring.validation.impl;

import com.fomov.newsmanagementspring.validation.INewsValidation;

import java.sql.Date;

public class NewsValidationImpl implements INewsValidation {
    @Override
    public boolean checkNewsData(String title, String brief, String content) {
        if(title != "" && brief != "" && content != "") {
            return true;
        }

        return false;
    }
}
