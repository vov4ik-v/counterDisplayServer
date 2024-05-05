package com.spm.vasylyshyn.security;

public class SecurityConstants {
    public static final String SIGN_UP_URLS="/api/auth/**";
    public static final String CAR_URLS="/api/car/**";
    public static final String MODEL_URLS="/api/model/**";
    public static final String EMAIL_URLS="/api/email/**";
    public static final String NEWS_URLS="/api/news/**";
    public static final String SECRET="SecretKeyGenJWT";
    public static final String TOKEN_PREFIX="BEARER ";
    public static final String HEADER_STRING="Authorization";
    public static final String CONTENT_TYPE="application/json";
//    public static final long EXPIRATION_TIME= 600_000;// 10 min
}
