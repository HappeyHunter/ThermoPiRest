package com.dromree.thermopi.dbaccess.mongodb.repositories.mongodb;

import com.dromree.thermopi.dbaccess.data.authorisedtokens.AuthorisedToken;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorisedTokensRepository extends MongoRepository<AuthorisedToken, String> {

    AuthorisedToken findFirstByUser(String user);

    AuthorisedToken findFirstByToken(String token);

}
