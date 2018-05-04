package com.dromree.thermopi.rest.endpoint;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Base REST controller with some handy response methods
 *
 */
public abstract class BaseController {

    protected ResponseEntity<?> ok() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    protected ResponseEntity<?> ok(Object responseBody) {
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }
}
