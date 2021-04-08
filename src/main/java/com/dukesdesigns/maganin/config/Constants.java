package com.dukesdesigns.maganin.config;

/**
 * Application constants.
 */
public final class Constants {
    // Regex for acceptable logins
    public static final String LOGIN_REGEX = "^[_.@A-Za-z0-9-]*$";

    public static final String SYSTEM_ACCOUNT = "system";
    public static final String ANONYMOUS_USER = "anonymoususer";

    public static final String DEFAULT_LANGUAGE = "ar";

    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    public static final String LOCAL_DATE_FORMAT = "yyyy-MM-dd";
    public static final int ARTICLES_LIST_SIZE = 10;

    public static final String ARTICLE_DB_KEYWORD = "مقال";
    private Constants() {}
}
