import java.security.NoSuchAlgorithmException;

public interface ObjectHashable {
    default String hexDigest() throws NoSuchAlgorithmException {
        StringBuilder sb = new StringBuilder();
        for (byte b : getBytes()) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
    
    byte[] getBytes() throws NoSuchAlgorithmException;
}
