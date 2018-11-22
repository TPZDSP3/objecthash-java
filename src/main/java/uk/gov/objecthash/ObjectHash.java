package uk.gov.objecthash;

import java.time.Instant;
import java.util.Map;

public final class ObjectHash {
    public static String toHexDigest (String value)  {
       return new StringValue(value).hexDigest();
    }

    public static String toHexDigest (Integer value)  {
        return new IntegerValue(value).hexDigest();
    }

    public static String toHexDigest (Instant value)  {
       return new TimestampValue(value).hexDigest();
    }

    public String hexDigest (Map<String, ObjectHashable> value) {
        return new DictValue(value).hexDigest();
    }
}
