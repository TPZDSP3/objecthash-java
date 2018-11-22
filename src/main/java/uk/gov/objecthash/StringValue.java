package uk.gov.objecthash;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import uk.gov.objecthash.exceptions.InvalidRedactedValue;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

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
                throw new InvalidRedactedValue("Invalid hex digest: " + hexDigest, e);
            }
        }
        
        return ObjectHashable.digest(ObjectHashable.STRING_TAG, value);
    }
}
