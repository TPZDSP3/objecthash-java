package uk.gov.objecthash;

import java.time.Instant;

public class TimestampValue implements ObjectHashable {

    private final Instant value;

    public TimestampValue(Instant value) {
        this.value = value;
    }

    @Override
    public byte[] digest() {
        return ObjectHashable.digest(ObjectHashable.TIMESTAMP_TAG, value.toString());
    }
}
