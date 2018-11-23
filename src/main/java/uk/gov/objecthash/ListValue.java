package uk.gov.objecthash;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListValue implements ObjectHashable {

    private final List<ObjectHashable> values;

    public ListValue(List<ObjectHashable> data) {
        this.values = new ArrayList<>(data);
    }
    
    @Override
    public byte[] digest() {
        MessageDigest sha256 = ObjectHashable.sha256Instance();
        sha256.update(ObjectHashable.LIST_TAG.getBytes(StandardCharsets.UTF_8)); 
        
        values.stream()
                .filter(Objects::nonNull)
                .map(ObjectHashable::digest)
                .forEach(sha256::update);

        return sha256.digest();
    }
}
