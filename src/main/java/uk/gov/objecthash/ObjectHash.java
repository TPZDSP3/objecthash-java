package uk.gov.objecthash;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.Normalizer;
import java.time.Instant;

public final class ObjectHash {
    
    private static final String STRING_TAG = "u";
    private static final String INTEGER_TAG = "i";
    private static final String TIMESTAMP_TAG = "t";

    private static String toHexDigest (String tag, String value) {
        MessageDigest sha256 = sha256Instance();
        sha256.update(tag.getBytes(StandardCharsets.UTF_8));
        String normalisedValue = Normalizer.normalize(value, Normalizer.Form.NFC);
        sha256.update(normalisedValue.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (byte b : sha256.digest()) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
    
    private static MessageDigest sha256Instance() {
        try {
            return MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("can't happen", e);
        }
    }

    public static String toHexDigest (String value) {
       return toHexDigest(STRING_TAG, value);
    }   
    
    public static String toHexDigest (Integer value)  {
       return toHexDigest(INTEGER_TAG, value.toString());
    }  
    
    public static String toHexDigest (Instant value)  {
       return toHexDigest(TIMESTAMP_TAG, value.toString());
    }

}
