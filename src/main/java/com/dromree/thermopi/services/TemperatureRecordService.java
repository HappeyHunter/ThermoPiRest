package com.dromree.thermopi.services;

import com.dromree.thermopi.dbaccess.data.temperaturelog.SensorReading;
import com.dromree.thermopi.dbaccess.data.temperaturelog.TemperatureRecord;
import com.dromree.thermopi.dbaccess.mongodb.MongoDBConfig;
import com.dromree.thermopi.dbaccess.mongodb.repositories.mongodb.TemperatureRecordRepository;
import com.dromree.thermopi.rest.data.TemperatureRecordData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Service
public class TemperatureRecordService {

    private TemperatureRecordRepository temperatureRecordRepository;

    private MongoTemplate mongoTemplate;

    @Autowired
    public TemperatureRecordService(TemperatureRecordRepository temperatureRecordRepository, MongoTemplate mongoTemplate) {
        this.temperatureRecordRepository = temperatureRecordRepository;
        this.mongoTemplate = mongoTemplate;
    }

    public TemperatureRecordData getCurrentTemperature() {
        TemperatureRecordData temperatureRecordData = null;
        Calendar now = Calendar.getInstance();

        TemperatureRecord temperatureRecord = temperatureRecordRepository.findFirstByYearAndMonthAndDay(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH));

        if (temperatureRecord != null) {
            temperatureRecordData = new TemperatureRecordData(temperatureRecord.getLastReading().getTemperature(), temperatureRecord.getLastReading().getHumidity());
        } else if(now.get(Calendar.HOUR_OF_DAY) == 0) {
            // Rare case that it's really early and the time hasn't updated for the current day yet so take yesterdays
            now.add(Calendar.HOUR_OF_DAY, -1);
            temperatureRecord = temperatureRecordRepository.findFirstByYearAndMonthAndDay(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH));

            if (temperatureRecord != null) {
                temperatureRecordData = new TemperatureRecordData(temperatureRecord.getLastReading().getTemperature(), temperatureRecord.getLastReading().getHumidity());
            }
        }

        return temperatureRecordData;
    }

    public void recordCurrentTemperature(TemperatureRecordData currentData) {
        Calendar now = Calendar.getInstance();

        TemperatureRecord temperatureRecord = temperatureRecordRepository.findFirstByYearAndMonthAndDay(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH));

        SensorReading sensorReading = new SensorReading(currentData.getTemperature(), currentData.getHumidity());

        if (temperatureRecord != null) {
            Update update = new Update().set("lastReading", sensorReading).set("minutes."+minuteOfDay(now), sensorReading);
            mongoTemplate.updateFirst(new Query(where("_id").is(temperatureRecord.getId())), update, TemperatureRecord.class);
        } else {
            Map<Integer, SensorReading> minutesMap = new HashMap<>();
            minutesMap.put(minuteOfDay(now), sensorReading);
            temperatureRecord = new TemperatureRecord(now.get(Calendar.YEAR),
                    now.get(Calendar.MONTH),
                    now.get(Calendar.DAY_OF_MONTH),
                    sensorReading,
                    minutesMap);
            temperatureRecordRepository.save(temperatureRecord);
        }

    }

    private Integer minuteOfDay(Calendar readingCalendar) {
        return readingCalendar.get(Calendar.HOUR_OF_DAY) * 60 + readingCalendar.get(Calendar.MINUTE);
    }
}
