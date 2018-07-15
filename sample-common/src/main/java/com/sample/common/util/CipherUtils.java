package com.sample.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Slf4j
public class CipherUtils {

    private static final String AES_SECRETKEY = "AES_SECRETKEY";

    public static final String AES = "AES";
    public static String secretKey = null;

    static {
        try {
            secretKey = System.getenv(AES_SECRETKEY.toString());
        } finally {
            secretKey = "1234567890123456";
        }
    }

    /**
     * 文字列を16文字の秘密鍵でAES暗号化してBase64した文字列で返す
     */
    public static String encryptAES(String originalSource) {
        try {
            byte[] originalBytes = originalSource.getBytes();
            byte[] encryptBytes = cipher(Cipher.ENCRYPT_MODE, originalBytes, secretKey, AES);
            byte[] encryptBytesBase64 = Base64.getEncoder().encode(encryptBytes);
            return new String(encryptBytesBase64);
        } catch (Exception e) {
            log.error("Original:" + originalSource + ", StackTrace:" + ExceptionUtils.getStackTrace(e));
            throw new RuntimeException(e);
        }
    }

    /**
     * Base64されたAES暗号化文字列を元の文字列に復元する
     */
    public static String decrypt(String encryptBytesBase64String, String secretKey)
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException,
            BadPaddingException {

        byte[] encryptBytes = Base64.getDecoder().decode(encryptBytesBase64String);
        byte[] originalBytes = cipher(Cipher.DECRYPT_MODE, encryptBytes, secretKey, AES);
        return new String(originalBytes);
    }

    /**
     * 暗号化/複合化の共通部分
     */
    private static byte[] cipher(int mode, byte[] source, String secretKey, String algorithm)
            throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException,
            BadPaddingException {
        byte[] secretKeyBytes = secretKey.getBytes();

        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKeyBytes, algorithm);
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(mode, secretKeySpec);
        return cipher.doFinal(source);
    }
}
