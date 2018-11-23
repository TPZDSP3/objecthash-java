package uk.gov.objecthash;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class RawValue implements ObjectHashable {
    private final byte[] hash;

    public RawValue(byte[] hash) {
        this.hash = hash;
    } 
    
    @Override
    public byte[] digest() {
        MessageDigest sha256 = ObjectHashable.sha256Instance();
        sha256.update(ObjectHashable.RAW_TAG.getBytes(StandardCharsets.UTF_8));
        sha256.update(hash);
        return sha256.digest();
    }
}
