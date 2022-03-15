package br.gasmartins.basket.application.web;

import br.gasmartins.basket.application.web.dto.ErrorDTO;
import br.gasmartins.basket.application.web.dto.ErrorDTO.ErrorFieldDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

import static net.logstash.logback.argument.StructuredArguments.kv;

@RestControllerAdvice
@Slf4j
public class ExceptionHandlerController {

    @ExceptionHandler(WebExchangeBindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleMethodArgumentNotValidException(WebExchangeBindException ex) {
        log.warn("Invalid Request: {}", kv("request", ex));
        ErrorDTO errorDTO = ErrorDTO.builder()
                .withCode(400)
                .withMessage(ex.getMessage())
                .build();
        ex.getFieldErrors().forEach(fieldError -> {
            var errorFieldDTO = ErrorFieldDTO.builder()
                    .withName(fieldError.getField())
                    .withValue(fieldError.getRejectedValue())
                    .withMessage(fieldError.getDefaultMessage())
                    .build();
            errorDTO.addField(errorFieldDTO);
        });
        return errorDTO;
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDTO handleException(Exception ex) {
        log.error("Error processing request: {}", kv("exception", ex));
        return ErrorDTO.builder()
                .withCode(500)
                .withMessage(ex.getMessage())
                .build();
    }
}
