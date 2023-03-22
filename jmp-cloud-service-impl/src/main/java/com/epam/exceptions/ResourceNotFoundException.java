package com.epam.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "%s is not found with id: %d";

    public ResourceNotFoundException(String resourceName, long id) {
        super(String.format(DEFAULT_MESSAGE, resourceName, id));
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}