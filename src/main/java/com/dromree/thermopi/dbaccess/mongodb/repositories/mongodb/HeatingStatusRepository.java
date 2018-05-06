package com.dromree.thermopi.dbaccess.mongodb.repositories.mongodb;

import com.dromree.thermopi.dbaccess.data.heatingstatus.HeatingStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeatingStatusRepository extends MongoRepository<HeatingStatus, String> {

    public HeatingStatus findTopByOrderByStatusTimeDesc();

}
