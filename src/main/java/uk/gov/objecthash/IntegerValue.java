package uk.gov.objecthash;

public class IntegerValue implements ObjectHashable {
    private final Integer value;

    public IntegerValue(Integer value) {
        this.value = value;
    }

    @Override
    public byte[] digest() {
        return ObjectHashable.digest(ObjectHashable.INTEGER_TAG, value.toString());
    }
}
