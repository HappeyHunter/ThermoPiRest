package com.dromree.thermopi.services;

import com.dromree.thermopi.dbaccess.data.temperaturelog.SensorReading;
import com.dromree.thermopi.dbaccess.data.temperaturelog.TemperatureRecord;
import com.dromree.thermopi.dbaccess.mongodb.repositories.mongodb.TemperatureRecordRepository;
import com.dromree.thermopi.rest.data.TemperatureRecordData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.data.mongodb.core.query.Criteria.where;

/**
 * Services for TemperatureRecords
 */
@Service
public class TemperatureRecordService {

    private TemperatureRecordRepository temperatureRecordRepository;

    private MongoTemplate mongoTemplate;

    @Autowired
    public TemperatureRecordService(TemperatureRecordRepository temperatureRecordRepository, MongoTemplate mongoTemplate) {
        this.temperatureRecordRepository = temperatureRecordRepository;
        this.mongoTemplate = mongoTemplate;
    }

    /**
     * Gets the most recent temperature recorded.
     * Checks the current day and only checks the previous day if this is the first hour of the current day.
     *
     * @return  The current temperature
     */
    public TemperatureRecordData getCurrentTemperature() {
        TemperatureRecordData temperatureRecordData = null;
        LocalDateTime now = LocalDateTime.now();

        TemperatureRecord temperatureRecord = temperatureRecordRepository.findFirstByYearAndMonthAndDay(now.getYear(), now.getMonthValue(), now.getDayOfMonth());

        if (temperatureRecord != null) {
            temperatureRecordData = new TemperatureRecordData(temperatureRecord.getLastReading().getTemperature(), temperatureRecord.getLastReading().getHumidity());
        } else if(now.getHour() == 0) {
            // Rare case that it's really early and the time hasn't updated for the current day yet so take yesterdays
            now = now.minusHours(1);
            temperatureRecord = temperatureRecordRepository.findFirstByYearAndMonthAndDay(now.getYear(), now.getMonthValue(), now.getDayOfMonth());

            if (temperatureRecord != null) {
                temperatureRecordData = new TemperatureRecordData(temperatureRecord.getLastReading().getTemperature(), temperatureRecord.getLastReading().getHumidity());
            }
        }

        return temperatureRecordData;
    }

    /**
     * Records the current temperature in the database
     *
     * @param currentData The current temperature
     */
    public void recordCurrentTemperature(TemperatureRecordData currentData) {
        LocalDateTime now = LocalDateTime.now();

        TemperatureRecord temperatureRecord = temperatureRecordRepository.findFirstByYearAndMonthAndDay(now.getYear(), now.getMonthValue(), now.getDayOfMonth());

        SensorReading sensorReading = new SensorReading(currentData.getTemperature(), currentData.getHumidity());

        if (temperatureRecord != null) {
            Update update = new Update().set("lastReading", sensorReading).set("minutes."+minuteOfDay(now), sensorReading);
            mongoTemplate.updateFirst(new Query(where("_id").is(temperatureRecord.getId())), update, TemperatureRecord.class);
        } else {
            Map<Integer, SensorReading> minutesMap = new HashMap<>();
            minutesMap.put(minuteOfDay(now), sensorReading);
            temperatureRecord = new TemperatureRecord(now.getYear(),
                    now.getMonthValue(),
                    now.getDayOfMonth(),
                    sensorReading,
                    minutesMap);
            temperatureRecordRepository.save(temperatureRecord);
        }

    }

    /**
     * Gets the current minute of the current day
     *
     * @param readingDateTime   LocalDateTime object to use
     * @return                  The current minute of the current day
     */
    private Integer minuteOfDay(LocalDateTime readingDateTime) {
        return readingDateTime.getHour() * 60 + readingDateTime.getMinute();
    }
}
