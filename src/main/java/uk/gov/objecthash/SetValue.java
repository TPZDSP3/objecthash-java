package uk.gov.objecthash;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Objects;
import java.util.Set;

public class SetValue implements ObjectHashable {

    private final Set<ObjectHashable> values;

    public SetValue(Set<ObjectHashable> values) {
        this.values = values;
    }

    @Override
    public byte[] digest() {
        MessageDigest sha256 = ObjectHashable.sha256Instance();
        sha256.update(ObjectHashable.SET_TAG.getBytes(StandardCharsets.UTF_8));
        
        values.stream()
                .filter(Objects::nonNull)
                .map(ObjectHashable::digest)
                .sorted(Util::compareBytes)
                .forEach(sha256::update);

        return sha256.digest();
    }
}
