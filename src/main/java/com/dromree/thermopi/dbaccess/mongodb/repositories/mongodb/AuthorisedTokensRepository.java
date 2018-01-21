package com.dromree.thermopi.dbaccess.mongodb.repositories.mongodb;

import com.dromree.thermopi.dbaccess.data.authorisedtokens.AuthorisedToken;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuthorisedTokensRepository extends MongoRepository<AuthorisedToken, String> {

    public AuthorisedToken findFirstByUser(String user);

    public AuthorisedToken findFirstByToken(String token);

}
