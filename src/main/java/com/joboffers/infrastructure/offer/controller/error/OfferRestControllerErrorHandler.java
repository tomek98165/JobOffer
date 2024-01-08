package com.joboffers.infrastructure.offer.controller.error;


import com.joboffers.domain.offer.OfferNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Log4j2
public class OfferRestControllerErrorHandler {


    @ExceptionHandler(OfferNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public OfferRestControllerErrorResponse errorHandleOfferNotFound(OfferNotFoundException e){
        String message = e.getMessage();
        log.error(message);
        return OfferRestControllerErrorResponse.builder()
                .message(message)
                .status(HttpStatus.NOT_FOUND)
                .build();
    }

    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.CONFLICT)
    public OfferRestControllerErrorResponse errorHandleDuplicateUrl(DuplicateKeyException e){
        String message = "Offer already exist";
        log.error(message);
        return OfferRestControllerErrorResponse.builder()
                .message(message)
                .status(HttpStatus.CONFLICT)
                .build();
    }
}
