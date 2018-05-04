package com.dromree.thermopi.rest.endpoint;

import com.dromree.thermopi.rest.data.AuthorisedTokenData;
import com.dromree.thermopi.services.AuthorisedTokensServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Rest Controller for Authorised Tokens
 */
@RestController
@RequestMapping("ThermoPi/Secure/AuthorizedTokens")
public class AuthorisedTokensController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(AuthorisedTokensController.class.getName());

    private final AuthorisedTokensServices authorisedTokensServices;

    @Autowired
    public AuthorisedTokensController(AuthorisedTokensServices authorisedTokensServices) {
        this.authorisedTokensServices = authorisedTokensServices;
    }

    /**
     * Adds the new Authorised Token
     *
     * @param tokenData The token to add
     */
    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> addAuthorisedToken(@RequestBody AuthorisedTokenData tokenData) {
        long startTime = System.currentTimeMillis();
        authorisedTokensServices.addTokenForUser(tokenData);
        logger.debug("addAuthorisedToken: " + (System.currentTimeMillis()-startTime));

        return ok();
    }

    /**
     * Gets a list of the Authorised Tokens
     *
     * @return  List of  all Authorised Tokens. Empty list if none are found.
     */
    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getAuthorisedTokens() {
        long startTime = System.currentTimeMillis();
        List<AuthorisedTokenData> tokens = authorisedTokensServices.getAllTokens();
        logger.debug("getAuthorisedTokens: " + (System.currentTimeMillis()-startTime));

        return ok(tokens);
    }
}
