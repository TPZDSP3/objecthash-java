package uk.gov.objecthash;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

public class ListValue implements ObjectHashable {

    private final List<ObjectHashable> values;

    public ListValue(List<ObjectHashable> data) {
        this.values = new ArrayList<>(data);
    }
    
    @Override
    public byte[] digest() {
        MessageDigest sha256 = ObjectHashable.sha256Instance();

        ArrayList<byte[]> elementHashes = new ArrayList<>();
        values.forEach(value -> {
            if (value != null) {
                elementHashes.add(value.digest());
            }
        });

        sha256.update(ObjectHashable.LIST_TAG.getBytes(StandardCharsets.UTF_8));
        elementHashes.forEach(sha256::update);

        return sha256.digest();
    }
}
