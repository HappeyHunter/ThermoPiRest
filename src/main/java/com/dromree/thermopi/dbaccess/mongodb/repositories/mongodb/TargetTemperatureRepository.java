package com.dromree.thermopi.dbaccess.mongodb.repositories.mongodb;

import com.dromree.thermopi.dbaccess.data.targettemperature.TargetTemperature;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TargetTemperatureRepository extends MongoRepository<TargetTemperature, String> {

}
