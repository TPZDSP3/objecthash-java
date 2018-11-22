package uk.gov.objecthash.exceptions;

public class InvalidRedactedValue extends RuntimeException {
    public InvalidRedactedValue(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
