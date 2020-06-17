package com.funtl.hello.spring.boot.util;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.io.IOException;
import java.security.SecureRandom;

public class DesUtil {
    private static final String DES = "DES";
    public static final String KEY = "foundern";

//    public static void main(String[] args) throws Exception {
//        String data = "fztest";
//        System.err.println(encrypt(data, "foundern"));
//        System.err.println(decrypt(encrypt(data, "foundern")));
//        System.out.println(Hex.encodeHex("J1lThTPwbliDUE4wxdbutQ==".getBytes()));
//    }
    // 加密
    public static String encrypt(String data, String password) throws Exception {
        byte[] bt = encrypt(data.getBytes(), password.getBytes());
        String strs = new String(Hex.encodeHex(bt));
        return strs;
    }

    // 解密
    public static String decrypt(String data) throws IOException {
        if (data == null) {
            return null;
        } else {
            byte[] bt;
            try {
                byte[] buf = Hex.decodeHex(data.toCharArray());
                bt = decrypt(buf, KEY.getBytes());
            } catch (Exception var3) {
                return "非法操作";
            }

            return new String(bt);
        }
    }

    private static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        SecureRandom sr = new SecureRandom();
        DESKeySpec dks = new DESKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);
        Cipher cipher = Cipher.getInstance(DES);
        cipher.init(1, securekey, sr);
        return cipher.doFinal(data);
    }

    private static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        SecureRandom sr = new SecureRandom();
        DESKeySpec dks = new DESKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);
        Cipher cipher = Cipher.getInstance(DES);
        cipher.init(2, securekey, sr);
        return cipher.doFinal(data);
    }
}
