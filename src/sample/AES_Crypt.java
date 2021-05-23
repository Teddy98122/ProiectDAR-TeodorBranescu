package sample;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

//Clasa de criptatare

public class AES_Crypt {

    private static SecretKeySpec cheieSecreta;
    private static byte[] cheie;


    public static String encrypt(String strDeCriptat, String secreta)
    {
        try
        {
            setKey(secreta);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, cheieSecreta);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strDeCriptat.getBytes("UTF-8")));
        }
        catch (Exception e)
        {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }

    public static String decrypt(String strDeDecriptat, String secret)
    {
        try
        {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, cheieSecreta);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strDeDecriptat)));
        }
        catch (Exception e)
        {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }

    public static void setKey(String myKey)
    {
        MessageDigest sha = null;
        try {
            cheie = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            cheie = sha.digest(cheie);
            cheie = Arrays.copyOf(cheie, 16);
            cheieSecreta = new SecretKeySpec(cheie, "AES");
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

}