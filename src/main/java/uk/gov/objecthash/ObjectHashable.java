package uk.gov.objecthash;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.Normalizer;

public interface ObjectHashable {
    String STRING_TAG = "u";
    String INTEGER_TAG = "i";
    String TIMESTAMP_TAG = "t";
    String DICT_TAG = "d";
    String SET_TAG = "s";
    String LIST_TAG = "l";
    String RAW_TAG = "r";

    static byte[] digest(String tag, String value) {
        MessageDigest sha256 = sha256Instance();
        sha256.update(tag.getBytes(StandardCharsets.UTF_8));
        String normalisedValue = Normalizer.normalize(value, Normalizer.Form.NFC);
        sha256.update(normalisedValue.getBytes(StandardCharsets.UTF_8));
        return sha256.digest();
    }

    static MessageDigest sha256Instance() {
        try {
            return MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("can't happen", e);
        }
    }
    
    default String hexDigest() {
        StringBuilder sb = new StringBuilder();
        for (byte b : digest()) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }


    default StringValue redacted() {
        return new StringValue("**REDACTED**" + hexDigest());
    }
    
    byte[] digest();
}
