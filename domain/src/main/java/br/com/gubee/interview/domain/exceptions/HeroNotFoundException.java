package br.com.gubee.interview.domain.exceptions;

public class HeroNotFoundException extends RuntimeException {
    public HeroNotFoundException(String message) {
        super(message);
    }
}
