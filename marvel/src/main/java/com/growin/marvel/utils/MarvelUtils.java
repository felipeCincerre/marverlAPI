package com.growin.marvel.utils;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

public class MarvelUtils {

    public static String retriveMD5(String ts, String privateKey, String publicKey){
        String concat = ts.concat(privateKey).concat(publicKey);
        byte[] bytesOfMessage = concat.getBytes(StandardCharsets.UTF_8);

        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        assert md != null;
        byte[] digest = md.digest(bytesOfMessage);
        return DatatypeConverter.printHexBinary(digest).toLowerCase(Locale.ROOT);
    }
}
