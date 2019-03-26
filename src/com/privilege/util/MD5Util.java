package com.privilege.util;

import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class MD5Util {
    private static String util="!";
    public static String MD5Encoding(String pass){
        //初始化MD5
        String encoder =null;
        MessageDigest md5 =null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            //创建base64的编码格式
            BASE64Encoder base64Encoder = new BASE64Encoder();
            encoder = base64Encoder.encode(pass.getBytes("utf-8"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encoder;
    }
}
