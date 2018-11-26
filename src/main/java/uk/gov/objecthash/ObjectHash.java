package uk.gov.objecthash;

import com.fasterxml.jackson.databind.JsonNode;

import java.time.Instant;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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

    public static String toHexDigest(byte[] value) {
        return new RawValue(value).hexDigest();
    }

    public static String toHexDigest(JsonNode json) {
        Iterator<Map.Entry<String, JsonNode>> fields = json.fields();
        var stream = StreamSupport.stream(Spliterators.spliteratorUnknownSize(fields, Spliterator.ORDERED), false);
        return stream.map(node -> {
                    var key = node.getKey();
                    var value = node.getValue().asText();
                    Map<String, ObjectHashable> data = new HashMap<>(){{
                        put(key, new StringValue(value));
                    }};
                    return ObjectHash.toHexDigest(data);
                }
        ).collect(Collectors.joining(""));
    }
}
