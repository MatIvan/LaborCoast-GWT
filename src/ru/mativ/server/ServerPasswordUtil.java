package ru.mativ.server;

import java.security.MessageDigest;
import java.util.Formatter;
import java.util.UUID;

public class ServerPasswordUtil {
    public static String calcSHA1Hash(String arg) {
        String hash = null;
        try {
            MessageDigest sha1 = MessageDigest.getInstance("SHA1");
            hash = calculateHash(sha1, arg);
        } catch (Exception e) {
            return null;
        }
        return hash;
    }

    private static String calculateHash(MessageDigest algorithm, String message) throws Exception {
        algorithm.update(message.getBytes());
        byte[] hash = algorithm.digest();
        return byteArray2Hex(hash);
    }

    private static String byteArray2Hex(byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String res = formatter.toString();
        formatter.close();
        return res;
    }

    public static String generateToken() {
        return UUID.randomUUID().toString();
    }
}
