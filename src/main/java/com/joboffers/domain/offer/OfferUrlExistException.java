package com.joboffers.domain.offer;

public class OfferUrlExistException extends RuntimeException {
    public OfferUrlExistException(String message) {
        super(message);
    }
}
