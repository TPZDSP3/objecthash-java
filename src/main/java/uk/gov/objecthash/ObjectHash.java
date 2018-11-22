package uk.gov.objecthash;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.Normalizer;

public final class ObjectHash {
    
    
    private static final String STRING_TAG = "u";
    private static final String INTEGER_TAG = "i";

    private static String toHexDigest (String tag, String value) throws NoSuchAlgorithmException {
        MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
        sha256.update(tag.getBytes(StandardCharsets.UTF_8));
        String normalisedValue = Normalizer.normalize(value, Normalizer.Form.NFC);
        sha256.update(normalisedValue.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (byte b : sha256.digest()) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public static String stringToHexDigest (String value) throws NoSuchAlgorithmException {
       return toHexDigest(STRING_TAG, value);
    }    
    public static String integerToHexDigest (Integer value) throws NoSuchAlgorithmException {
       return toHexDigest(INTEGER_TAG, value.toString());
    }
}
