package com.funtl.hello.spring.boot.util;

import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import org.apache.commons.codec.binary.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * @author qy
 * @date 2020/6/18 11:00
 * @description
 */
public class DES {

    /**
     * 加密
     *
     * @param data     要加密的数据
     * @param password 密钥(8位以上)
     * @return 密文
     * @throws Exception
     */
    public static byte[] encrypt(String data, String password) throws Exception {
        SecureRandom random = new SecureRandom();
        DESKeySpec desKeySpec = new DESKeySpec(StringUtils.getBytesUtf8(password));
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(SymmetricAlgorithm.DES.getValue());

        Cipher cipher = Cipher.getInstance(SymmetricAlgorithm.DES.getValue());
        cipher.init(Cipher.ENCRYPT_MODE, keyFactory.generateSecret(desKeySpec), random);

        return cipher.doFinal(StringUtils.getBytesUtf8(data));
    }

    /**
     * 加密成base64字符串
     *
     * @param data     要加密的数据
     * @param password 密钥(8位以上)
     * @return base64加密后的密文
     * @throws Exception
     */
    public static String encryptBase64(String data, String password) throws Exception {
        return Base64.getEncoder().encodeToString(encrypt(data, password));
    }

    /**
     * 解密base64后的密文
     *
     * @param data     base64加密后的密文
     * @param password 密钥(8位以上)
     * @return
     */
    public static String decryptBase64(String data, String password) {
        try {
            SecureRandom random = new SecureRandom();
            DESKeySpec desKeySpec = new DESKeySpec(StringUtils.getBytesUtf8(password));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(SymmetricAlgorithm.DES.getValue());

            Cipher cipher = Cipher.getInstance(SymmetricAlgorithm.DES.getValue());
            cipher.init(Cipher.DECRYPT_MODE, keyFactory.generateSecret(desKeySpec), random);

            byte[] dataBytes = Base64.getDecoder().decode(data);
            return StringUtils.newStringUtf8(cipher.doFinal(dataBytes));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
