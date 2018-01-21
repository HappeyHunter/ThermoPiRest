package com.dromree.thermopi.dbaccess.mongodb.repositories.mongodb;

import com.dromree.thermopi.dbaccess.data.temperaturelog.TemperatureRecord;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface TemperatureRecordRepository extends MongoRepository<TemperatureRecord, String> {

    public TemperatureRecord findTemperatureRecordByYearAndMonthAndDay(Integer year, Integer month, Integer day);

    @Query(fields = "{ 'lastReading' : 1 }")
    public TemperatureRecord findFirstByYearAndMonthAndDay(Integer year, Integer month, Integer day);
}
