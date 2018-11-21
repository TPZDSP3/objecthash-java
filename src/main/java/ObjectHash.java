import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.Normalizer;

public class ObjectHash {
    private static final String STRING_TAG = "u";

    public String hexDigest (String value) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
        sha256.update(STRING_TAG.getBytes("UTF-8"));

        // TODO: this needs to UTF8 encode and do unicode normalisation
        String normalisedValue = Normalizer.normalize(value, Normalizer.Form.NFC);
        sha256.update(normalisedValue.getBytes("UTF-8"));
        StringBuilder sb = new StringBuilder();
        for (byte b : sha256.digest()) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
