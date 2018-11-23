package uk.gov.objecthash;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

public class StringValue implements ObjectHashable {
    private final String value;
    
    public StringValue(String value) {
        this.value = value;
    }
    
    public byte[] digest() {
        if(value.startsWith("**REDACTED**")) {
            final String hexDigest = value.substring(12);
            try {
                return Hex.decodeHex(hexDigest);
            } catch (DecoderException e) {
                /* 
                 * If the redacted value is not valid hex, then treat it as
                 * a non-redacted string. This ensures that every possible
                 * data structure has a hash. We do not enforce that the hash
                 * length matches any particular hashing algorithm, only that
                 * it is hex encoded.
                 */
            }
        }
        
        return ObjectHashable.digest(ObjectHashable.STRING_TAG, value);
    }
}
