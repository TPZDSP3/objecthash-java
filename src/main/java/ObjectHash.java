import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.Normalizer;

public final class ObjectHash {
    
    
    private static final String STRING_TAG = "u";

    public String hexDigest (String value) throws NoSuchAlgorithmException {
        MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
        sha256.update(STRING_TAG.getBytes(StandardCharsets.UTF_8));
        String normalisedValue = Normalizer.normalize(value, Normalizer.Form.NFC);
        sha256.update(normalisedValue.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (byte b : sha256.digest()) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
