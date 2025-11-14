package com.example.usermanagement.constant;

public class AppConstants {
    public static final String USER_KEY_PREFIX = "pending:user:";

    public static final String USER_LIST_PREFIX = "users:list:";

    public static final String USER_LIST_KEY_FORMAT =
            USER_LIST_PREFIX + "page=%d:size=%d:firstName=%s:lastName=%s";

    public static final String ALL_USERS = USER_LIST_PREFIX + "*";

    public static final String OTP_KEY_PREFIX = "OTP:";
    public static final String REFRESH_KEY_PREFIX = "auth:refresh:";
    public static final String ACCESS_BLACKLIST_PREFIX = "auth:black:access:";
}