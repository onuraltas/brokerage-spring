package com.brokerage.spring.exceptions;

import com.brokerage.spring.enums.ExceptionMessage;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class CustomizedResponseEntityExceptionsHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    public ProblemDetail handleOtherExceptions(Exception ex, WebRequest webRequest) {

        log.error("ERROR", ex);

        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        problemDetail.setTitle("ERROR");
        problemDetail.setDetail(ExceptionMessage.GENERAL_EXCEPTION.getErrorMessage());

        problemDetail.setInstance(URI.create(webRequest.getDescription(false)));

        return problemDetail;
    }

    @ExceptionHandler(value = {BrokerageException.class})
    public ProblemDetail handleCustomizedException(BrokerageException ex, WebRequest webRequest) {

        log.info("ERROR", ex);

        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("ERROR");
        problemDetail.setDetail(ex.getLocalizedMessage());
        problemDetail.setInstance(URI.create(webRequest.getDescription(false)));

        return problemDetail;
    }

    @ExceptionHandler(value = {InvalidDataAccessResourceUsageException.class})
    public ProblemDetail handleCustomizedException(InvalidDataAccessResourceUsageException ex, WebRequest webRequest) {

        log.info("ERROR", ex);

        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("ERROR");
        problemDetail.setDetail(ex.getLocalizedMessage());
        problemDetail.setInstance(URI.create(webRequest.getDescription(false)));

        return problemDetail;
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ProblemDetail handleCustomizedException(ConstraintViolationException ex, WebRequest webRequest) {

        log.info("ERROR", ex);

        String errorMessage = ex.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(", "));

        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle(ExceptionMessage.CONSTRAINT_VIOLATION.getErrorMessage());
        problemDetail.setDetail(errorMessage);
        problemDetail.setInstance(URI.create(webRequest.getDescription(false)));

        return problemDetail;
    }

    @ExceptionHandler(value = {DataIntegrityViolationException.class})
    public ProblemDetail handleCustomizedException(DataIntegrityViolationException ex, WebRequest webRequest) {

        log.info("ERROR", ex);

        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle(ExceptionMessage.GENERAL_EXCEPTION.getErrorMessage());
        problemDetail.setDetail(ExceptionMessage.GENERAL_EXCEPTION.getErrorMessage());
        problemDetail.setInstance(URI.create(webRequest.getDescription(false)));

        return problemDetail;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status,
                                                                  WebRequest webRequest) {

        log.info("ERROR", ex);

        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle(ExceptionMessage.CONSTRAINT_VIOLATION.getErrorMessage());
        problemDetail.setDetail(ex.getBindingResult().getFieldError().getDefaultMessage());
        problemDetail.setInstance(URI.create(webRequest.getDescription(false)));

        return new ResponseEntity<>(problemDetail, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

}
