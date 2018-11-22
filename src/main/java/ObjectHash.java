import java.security.NoSuchAlgorithmException;
import java.util.Map;

public final class ObjectHash {
    public static final String STRING_VALUE_TAG = "u";
    public static final String DICT_VALUE_TAG = "d";

    public String hexDigest (String value) throws NoSuchAlgorithmException {
        return new StringValue(value).hexDigest();
    }
    
    public String hexDigest (Map<String, ObjectHashable> value) throws NoSuchAlgorithmException {
        return new DictValue(value).hexDigest();
    } 
}
