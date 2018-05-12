package com.dromree.thermopi.rest.endpoint;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Base REST controller with some handy response methods
 *
 */
public abstract class BaseController {

    ResponseEntity<?> ok() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    ResponseEntity<?> ok(Object responseBody) {
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    ResponseEntity<?> notFound() {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
