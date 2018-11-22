package uk.gov.objecthash;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Set;

public class SetValue implements ObjectHashable {

    private final Set<ObjectHashable> values;

    public SetValue(Set<ObjectHashable> values) {
        this.values = values;
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
        elementHashes.sort(Util::compareBytes);

        sha256.update(ObjectHashable.SET_TAG.getBytes(StandardCharsets.UTF_8));
        elementHashes.forEach(sha256::update);

        return sha256.digest();
    }
}
