package br.com.gubee.interview.domain.exceptions;

public class CannotCompareTheSameHeroException extends RuntimeException {
    public CannotCompareTheSameHeroException(String message) {
        super(message);
    }
}
