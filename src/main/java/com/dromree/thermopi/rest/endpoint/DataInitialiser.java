package com.dromree.thermopi.rest.endpoint;

import com.dromree.thermopi.rest.data.*;
import com.dromree.thermopi.services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/ThermoPi/DataInitialiser")
public class DataInitialiser extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(DataInitialiser.class.getName());

    private final HeatingStatusServices heatingStatusServices;

    private final BoostServices boostServices;

    private final TemperatureRecordService temperatureRecordService;

    private final TargetTemperatureService targetTemperatureService;

    private final HeatingScheduleServices heatingScheduleServices;

    @Autowired
    public DataInitialiser(HeatingStatusServices heatingStatusServices, BoostServices boostServices, TemperatureRecordService temperatureRecordService, TargetTemperatureService targetTemperatureService, HeatingScheduleServices heatingScheduleServices) {
        this.heatingStatusServices = heatingStatusServices;
        this.boostServices = boostServices;
        this.temperatureRecordService = temperatureRecordService;
        this.targetTemperatureService = targetTemperatureService;
        this.heatingScheduleServices = heatingScheduleServices;
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> initialiseDBData() {

        try {
            // Setup the boost setting to disabled
            // Setup the current temperature with dud data 100 degrees
            // Setup the target temperature with dud data 0 degrees
            // Setup the schedule for each month - All disabled
            initBoost();
            initCurrentTemp();
            initTargetTemp();
            initSchedule();
            initHeatingStatus();
        } catch(Throwable t) {
            logger.error("Error occurred inistalising database", t);
            return new ResponseEntity<>(Boolean.FALSE, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // If all goes well return true

        return ok(Boolean.TRUE);
    }

    private void initHeatingStatus() {
        heatingStatusServices.setHeatingStatus(new HeatingStatusData(Boolean.FALSE));
    }

    private void initBoost() {
        BoostData boostData = new BoostData();

        boostData.setEnabled(Boolean.FALSE);

        boostServices.setBoostSetting(boostData);
    }

    private void initCurrentTemp() {
        temperatureRecordService.recordCurrentTemperature(new TemperatureRecordData(100d, 100d));
    }

    private void initTargetTemp() {
        targetTemperatureService.setTargetTemperature(new TargetTemperatureData(0d));
    }

    private void initSchedule() {
        QuarterScheduleData quarterScheduleData = new QuarterScheduleData(Boolean.FALSE);

        Map<String, QuarterScheduleData> quarterScheduleDataMap = new HashMap<>();

        for(int quarter = 0; quarter < 4; quarter++) {
            quarterScheduleDataMap.put(String.valueOf(quarter), quarterScheduleData);
        }

        HourScheduleData hourScheduleData = new HourScheduleData(quarterScheduleDataMap);

        Map<String, HourScheduleData> hourScheduleDataMap = new HashMap<>();

        for(int hour = 0; hour < 24; hour++) {
            hourScheduleDataMap.put(String.valueOf(hour), hourScheduleData);
        }

        DayScheduleData dayScheduleData = new DayScheduleData(hourScheduleDataMap);

        Map<String, DayScheduleData> dayScheduleDataMap = new HashMap<>();

        for(DayOfWeek day : DayOfWeek.values()) {
            dayScheduleDataMap.put(day.name(), dayScheduleData);
        }

        WeekScheduleData weekScheduleData = new WeekScheduleData();
        weekScheduleData.setDays(dayScheduleDataMap);

        for(int month = 1; month <= 12; month++) {
            weekScheduleData.setMonth(month);

            heatingScheduleServices.updateHeatingScheduleByWeek(weekScheduleData);
        }


    }
}
