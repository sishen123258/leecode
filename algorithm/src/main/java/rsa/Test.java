package rsa;

import net.lingala.zip4j.util.Zip4jConstants;

import javax.crypto.Cipher;
import java.security.*;
import java.util.Base64;

public class Test {

    static String plainText="ABC";

    public static void main(String[] args) throws Exception {

        KeyPair keyPair = generateKeyPair();

        PrivateKey aPrivate = keyPair.getPrivate();
        PublicKey aPublic = keyPair.getPublic();


        publicEncode(aPublic);
    }

    private static void publicEncode(PublicKey aPublic) throws Exception {

        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, aPublic);

        byte[] bytes = encryptCipher.doFinal(plainText.getBytes("UTF-8"));

        String encrypted = Base64.getEncoder().encodeToString(bytes);
        System.out.println("Public encoded value is "+ new String(encrypted));
    }


    public static KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048, new SecureRandom());
        KeyPair pair = generator.generateKeyPair();

        return pair;
    }



}
