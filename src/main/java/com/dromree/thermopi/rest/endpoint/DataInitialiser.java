package com.dromree.thermopi.rest.endpoint;

import com.dromree.thermopi.dbaccess.data.Days;
import com.dromree.thermopi.rest.data.*;
import com.dromree.thermopi.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/ThermoPi/DataInitialiser")
public class DataInitialiser {

    @Autowired
    private HeatingStatusServices heatingStatusServices;

    @Autowired
    private BoostServices boostServices;

    @Autowired
    private TemperatureRecordService temperatureRecordService;

    @Autowired
    private TargetTemperatureService targetTemperatureService;

    @Autowired
    private HeatingScheduleServices heatingScheduleServices;

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Boolean initialiseDBData(String initData) {

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
            return Boolean.FALSE;
        }

        // If all goes well return true

        return Boolean.TRUE;
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

        for(Days day : Days.values()) {
            dayScheduleDataMap.put(day.toString(), dayScheduleData);
        }

        WeekScheduleData weekScheduleData = new WeekScheduleData();
        weekScheduleData.setDays(dayScheduleDataMap);

        for(int month = 0; month < 12; month++) {
            weekScheduleData.setMonth(month);

            heatingScheduleServices.updateHeatingScheduleByWeek(weekScheduleData);
        }


    }
}
