package com.dromree.thermopi.dbaccess.mongodb.repositories.mongodb;

import com.dromree.thermopi.dbaccess.data.boost.Boost;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoostRepository extends MongoRepository<Boost, String> {

    Boost findTopByOrderByTimestampDesc();

}
