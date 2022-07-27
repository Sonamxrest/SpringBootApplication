package com.xrest.spring.Configuration;
//class to store default jwt properties
public class JWTProperties {
    public static final String SECRET_KEY = "secret_encoding_key";
    public static final int EXPIRATION_TIME = 86_00_000;
    public static final String TOKEN_TYPE = "Bearer";
    public static final String HEADER_TYPE = "Authorization";
}
