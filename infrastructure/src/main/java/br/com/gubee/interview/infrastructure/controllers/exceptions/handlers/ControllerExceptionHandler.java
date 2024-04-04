package br.com.gubee.interview.infrastructure.controllers.exceptions.handlers;

import br.com.gubee.interview.domain.exceptions.CannotCompareTheSameHeroException;
import br.com.gubee.interview.domain.exceptions.HeroNameAlreadyExistsException;
import br.com.gubee.interview.domain.exceptions.HeroNotFoundException;
import br.com.gubee.interview.domain.exceptions.InvalidPowerValueException;
import br.com.gubee.interview.infrastructure.controllers.exceptions.StandardError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class ControllerExceptionHandler {
    private static final Instant NOW = Instant.now();
    private static final HttpStatus BAD_REQUEST = HttpStatus.BAD_REQUEST;
    private static final HttpStatus NOT_FOUND = HttpStatus.NOT_FOUND;

    @ExceptionHandler(HeroNotFoundException.class)
    public ResponseEntity<StandardError> heroNotFoundException(HeroNotFoundException err, HttpServletRequest request) {
        String error = "hero_not_found_exception";
        StandardError standardError = new StandardError(
                NOW,
                BAD_REQUEST.value(),
                error,
                err.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(BAD_REQUEST).body(standardError);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<StandardError> emptyResultDataAccessException(EmptyResultDataAccessException err, HttpServletRequest request) {
        String error = "empty_result_data_access_exception";
        StandardError standardError = new StandardError(
                NOW,
                NOT_FOUND.value(),
                error,
                err.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(NOT_FOUND).body(standardError);
    }

    @ExceptionHandler(HeroNameAlreadyExistsException.class)
    public ResponseEntity<StandardError> heroNameAlreadyExistsException(HeroNameAlreadyExistsException err, HttpServletRequest request) {
        String error = "hero_name_already_exists_exception";
        StandardError standardError = new StandardError(
                NOW,
                BAD_REQUEST.value(),
                error,
                err.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(BAD_REQUEST).body(standardError);
    }

    @ExceptionHandler(InvalidPowerValueException.class)
    public ResponseEntity<StandardError> invalidPowerValueException(InvalidPowerValueException err, HttpServletRequest request) {
        String error = "invalid_power_value_exception";
        StandardError standardError = new StandardError(
                NOW,
                BAD_REQUEST.value(),
                error,
                err.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(BAD_REQUEST).body(standardError);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<StandardError> illegalArgumentException(IllegalArgumentException err, HttpServletRequest request) {
        String error = "illegal_argument_exception";
        StandardError standardError = new StandardError(
                NOW,
                BAD_REQUEST.value(),
                error,
                err.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(BAD_REQUEST).body(standardError);
    }

    @ExceptionHandler(CannotCompareTheSameHeroException.class)
    public ResponseEntity<StandardError> cannotCompareTheSameHeroException(CannotCompareTheSameHeroException err, HttpServletRequest request) {
        String error = "cannot_compare_the_same_hero";
        StandardError standardError = new StandardError(
                NOW,
                BAD_REQUEST.value(),
                error,
                err.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(BAD_REQUEST).body(standardError);
    }
}
