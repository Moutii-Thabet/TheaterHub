package com.moutii.TheaterHub.security;


import java.io.IOException;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class KeyUtils {
    private final static String privateKeyPath = "/keys/private_key.pem";
    private final static String publicKeyPath = "/keys/public_key.pem";

    private KeyUtils(){};

    public static PrivateKey getPrivateKey() throws Exception{
        final String pkey =  readFileText(privateKeyPath)
                .replace("-----BEGIN PRIVATE KEY-----","")
                .replace("-----END PRIVATE KEY-----","")
                .replaceAll("\\s+","");
        System.out.println(pkey);
        final byte[] decodedKey = Base64.getDecoder().decode(pkey);
        final PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decodedKey);
        return KeyFactory.getInstance("RSA").generatePrivate(keySpec);
    }

    public static PublicKey getPublicKey() throws Exception{
        final String pkey =  readFileText(publicKeyPath)
                .replace("-----BEGIN PUBLIC KEY-----","")
                .replace("-----END PUBLIC KEY-----","")
                .replaceAll("\\s+","");
        final byte[] decodedKey = Base64.getDecoder().decode(pkey);
        final X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decodedKey);
        return KeyFactory.getInstance("RSA").generatePublic(keySpec);
    }



    private static String readFileText(String keyPath) throws Exception {
        try(InputStream is = KeyUtils.class.getResourceAsStream(keyPath)) {
            if(is == null) {
                throw new IllegalAccessException("Resource not found with path " + keyPath);
            }
            return new String(is.readAllBytes());
        }
    }


}
