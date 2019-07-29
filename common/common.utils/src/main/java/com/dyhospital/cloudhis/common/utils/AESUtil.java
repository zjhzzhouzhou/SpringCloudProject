package com.dyhospital.cloudhis.common.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;


public class AESUtil {

    /**
     * AES加密tokenid获取longToken
     * @param tokenid
     * @param random
     * @param userKey·
     * @return
     * @throws Exception
     */
    public static String encryptToken(String tokenid, String random, String userKey) throws Exception {
        SecretKey generateKey = getKey(random, userKey);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(generateKey.getEncoded(), "AES"));
        byte[] result = cipher.doFinal(tokenid.getBytes("utf-8"));
        return base64Encode(result);
    }

    /**
     * AES解密cltk获取tokenid
     * @param longToken
     * @param random
     * @param userKey
     * @return
     * @throws Exception
     */
    public static String decryptToken(String longToken, String random, String userKey) throws Exception {
        SecretKey generateKey = getKey(random, userKey);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(generateKey.getEncoded(), "AES"));
        byte[] decryptBytes = cipher.doFinal(base64Decode(longToken));
        return new String(decryptBytes, "utf-8");
    }

    public static SecretKey getKey(String random, String userKey){
        String key = random + userKey;
        try {
            KeyGenerator generator = KeyGenerator.getInstance( "AES" );
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG" );
            secureRandom.setSeed(key.getBytes());
            generator.init(128,secureRandom);
            return generator.generateKey();
        }  catch (Exception e) {
            throw new RuntimeException(" 初始化密钥出现异常 " );
        }
    }

    /**
     * base 64 加密
     * @param bytes 待编码的byte[]
     * @return 编码后的base 64 code
     */
    public static String base64Encode(byte[] bytes){
        return new BASE64Encoder().encode(bytes);
    }

    /**
     * base 64 解密
     * @param base64Code 待解码的base 64 code
     * @return 解码后的byte[]
     * @throws Exception
     */
    public static byte[] base64Decode(String base64Code) throws Exception{
        return StringUtil.isEmpty(base64Code) ? null : new BASE64Decoder().decodeBuffer(base64Code);
    }



    public static void main(String[] args){
//        try {
//            String cltk = encryptToken("d39b37b98606404cad9077ae29884330", "468922", "14");
//            System.out.println(cltk);
//            System.out.println(decryptToken(cltk, "468922", "14"));
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date now = new Date();
//        System.out.println(sdf.format(now));
//        System.out.println(sdf.format(new Date(System.currentTimeMillis() + 30*60*1000)));
//        System.out.println(String.valueOf((int)((Math.random()*9+1)*100000)));

    }
}
