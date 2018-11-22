package uk.gov.objecthash;

public class StringValue implements ObjectHashable {
    private final String value;
    
    public StringValue(String value) {
        this.value = value;
    }
    
    public byte[] digest() {
        return ObjectHashable.digest(ObjectHashable.STRING_TAG, value);
    }
}
