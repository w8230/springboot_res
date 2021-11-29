package kr.co.team.res.common.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class AESEncryptor {

    //@Value("${EncryptKey}")
    //private static String encryptKey;

    private static byte[] key = null;
    private static final String ALGORITHM = "AES";

    //public static AESEncryptor instance = new AESEncryptor();

    private AESEncryptor(String encryptKey) {}

    public static AESEncryptor getInstance(String encryptKey) {
        key = encryptKey.getBytes(StandardCharsets.UTF_8);
        return new AESEncryptor(encryptKey);
    }

    public String encrypt(String plainText) throws Exception {
        if (plainText == null) {
            return null;
        }
        return Base64.getEncoder().encodeToString(this.encrypt(plainText.getBytes(StandardCharsets.UTF_8)));
    }

    public String decrypt(String encText) throws Exception {
        if (encText == null) {
            return null;
        }
        encText = encText.replace(' ', '+');
        return new String(decrypt(Base64.getDecoder().decode(encText)), StandardCharsets.UTF_8);
    }

    private byte[] encrypt(byte[] plainText) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(key, ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        return cipher.doFinal(plainText);
    }

    private byte[] decrypt(byte[] cipherText) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(key, ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        return cipher.doFinal(cipherText);
    }
}
