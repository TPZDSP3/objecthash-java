import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.Normalizer;

public class StringValue implements ObjectHashable {
    private final String value;
    
    public StringValue(String value) {
        this.value = value;
    }
    
    public byte[] getBytes() throws NoSuchAlgorithmException {
        MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
        sha256.update(ObjectHash.STRING_VALUE_TAG.getBytes(StandardCharsets.UTF_8));
        String normalisedValue = Normalizer.normalize(value, Normalizer.Form.NFC);
        sha256.update(normalisedValue.getBytes(StandardCharsets.UTF_8));
        return sha256.digest();
    }
}
