package objects;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class Encrypt {

    public static String encryptPassWord(String userName, String passWord) {
        StandardPBEStringEncryptor stringEncryptor = new StandardPBEStringEncryptor();
        stringEncryptor.setPassword(userName);
        String encryptedPassWord = stringEncryptor.encrypt(passWord);
        return encryptedPassWord;
    }

    public static String decryptPassWord(String userName, String encryptedPassWord) {
        StandardPBEStringEncryptor stringEncryptor = new StandardPBEStringEncryptor();
        stringEncryptor.setPassword(userName);
        String decryptedPassWord = stringEncryptor.decrypt(encryptedPassWord);
        return decryptedPassWord;
    }

}
