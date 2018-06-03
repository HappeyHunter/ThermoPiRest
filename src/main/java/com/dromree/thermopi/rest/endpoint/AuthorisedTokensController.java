package com.dromree.thermopi.rest.endpoint;

import com.dromree.thermopi.rest.data.AuthorisedTokenData;
import com.dromree.thermopi.services.AuthorisedTokensServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Rest Controller for Authorised Tokens
 */
@RestController
@RequestMapping("ThermoPi/Secure/AuthorizedTokens")
public class AuthorisedTokensController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(AuthorisedTokensController.class.getName());

    private final AuthorisedTokensServices authorisedTokensServices;

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
    public ResponseEntity<?> addAuthorisedToken(@RequestBody @Valid AuthorisedTokenData tokenData) {
        authorisedTokensServices.addTokenForUser(tokenData);

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
        List<AuthorisedTokenData> tokens = authorisedTokensServices.getAllTokens();

        return ok(tokens);
    }
}
