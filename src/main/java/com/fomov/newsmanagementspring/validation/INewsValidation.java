package com.fomov.newsmanagementspring.validation;

import java.sql.Date;

public interface INewsValidation {
    boolean checkNewsData(String title, String brief, String content);
}
