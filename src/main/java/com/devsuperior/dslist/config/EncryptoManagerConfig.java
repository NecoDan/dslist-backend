package com.devsuperior.dslist.config;

import org.jasypt.util.text.StrongTextEncryptor;

public class EncryptoManagerConfig {

    private static final StrongTextEncryptor encryptor;

    private EncryptoManagerConfig() {
        throw new IllegalStateException("This is a utility class EncryptoManagerConfig and cannot be instantiated");
    }

    static {
        encryptor = new StrongTextEncryptor();
        encryptor.setPassword(System.getenv("{APP_KEY_PWD_ENCRYPT}"));
    }

    public static String encrypt(String rawText) {
        return encryptor.encrypt(rawText);
    }

    public static String decrypt(String encryptedText) {
        return encryptor.decrypt(encryptedText);
    }
}
