package uk.gov.objecthash;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DictValue implements ObjectHashable {
    private final Map<String, ObjectHashable> data;
    
    public DictValue(Map<String, ObjectHashable> data) {
        this.data = data;
    }
    
    public byte[] digest() {
        MessageDigest sha256 = ObjectHashable.sha256Instance();
        
        List<byte[]> hashValues = new ArrayList<>();
        for (Map.Entry<String, ObjectHashable> entry : this.data.entrySet()) {
            ObjectHashable value = entry.getValue();
            ObjectHashable key = new StringValue(entry.getKey());
            if (value != null) {
                hashValues.add(concatEntryHashes(key.digest(), value.digest()));
            }
        }
        hashValues.sort(Util::compareBytes);
        sha256.update(ObjectHashable.DICT_TAG.getBytes(StandardCharsets.UTF_8));
        hashValues.forEach(sha256::update);
        
        return sha256.digest();
    }
    
    private byte[] concatEntryHashes(byte[] attrHash, byte[] valueHash) {
        byte[] concattedHash = new byte[attrHash.length + valueHash.length];
        System.arraycopy(attrHash, 0, concattedHash, 0, attrHash.length);
        System.arraycopy(valueHash, 0, concattedHash, attrHash.length, valueHash.length);
        return concattedHash;
    }
}
