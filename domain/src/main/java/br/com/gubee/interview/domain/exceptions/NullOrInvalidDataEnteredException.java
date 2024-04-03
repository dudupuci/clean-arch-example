package br.com.gubee.interview.domain.exceptions;
public class NullOrInvalidDataEnteredException extends RuntimeException {
    public NullOrInvalidDataEnteredException(String message) {
        super(message);
    }
}
