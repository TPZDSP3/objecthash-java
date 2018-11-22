package uk.gov.objecthash;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class ObjectHash {
    public static String toHexDigest(String value)  {
       return new StringValue(value).hexDigest();
    }

    public static String toHexDigest(Integer value)  {
        return new IntegerValue(value).hexDigest();
    }

    public static String toHexDigest(Instant value)  {
       return new TimestampValue(value).hexDigest();
    }

    public static String toHexDigest(Map<String, ObjectHashable> value) {
        return new DictValue(value).hexDigest();
    }

    public static String toHexDigest(Set<ObjectHashable> value) {
        return new SetValue(value).hexDigest();
    }

    public static String toHexDigest(List<ObjectHashable> value) {
        return new ListValue(value).hexDigest();
    }
}
