package br.com.vitor.minishop.util;

import liquibase.repackaged.org.apache.commons.lang3.RandomStringUtils;

public class PasswordGenerator {
    public static String generate() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~`!@#$%^&*()-_=+[{]}\\|;:\'\",<.>/?";
        return RandomStringUtils.random( 15, characters );
    }
}
