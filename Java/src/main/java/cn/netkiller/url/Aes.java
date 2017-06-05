package cn.netkiller.url;

/**
 * Created by ww on 2017/4/19.
 */
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Aes {

    public static String encrypt(String input, String key) {
        byte[] crypted = null;
        try {

            SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "AES");

            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, skey);
            crypted = cipher.doFinal(input.getBytes());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        java.util.Base64.Encoder encoder = java.util.Base64.getEncoder();

        return new String(encoder.encodeToString(crypted));
    }

    public static String decrypt(String input, String key) {
        byte[] output = null;
        try {
            java.util.Base64.Decoder decoder = java.util.Base64.getDecoder();
            SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skey);
            output = cipher.doFinal(decoder.decode(input));
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return new String(output);
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        String key = "mvLBiZsiTbGwrfJB";
        String data = "ABC";

        System.out.println(Aes.encrypt(data, key));
        System.out.println(Aes.decrypt(Aes.encrypt(data, key), key));
    }



}
