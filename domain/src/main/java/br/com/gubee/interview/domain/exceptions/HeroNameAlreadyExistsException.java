package br.com.gubee.interview.domain.exceptions;
public class HeroNameAlreadyExistsException extends RuntimeException {
    public HeroNameAlreadyExistsException(String message) {
        super(message);
    }
}
