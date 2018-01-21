package com.dromree.thermopi.services.util;

import com.dromree.thermopi.dbaccess.data.heatingschedule.DaySchedule;
import com.dromree.thermopi.dbaccess.data.heatingschedule.HourSchedule;
import com.dromree.thermopi.dbaccess.data.heatingschedule.QuarterSchedule;
import com.dromree.thermopi.dbaccess.data.heatingschedule.WeekSchedule;
import com.dromree.thermopi.rest.data.DayScheduleData;
import com.dromree.thermopi.rest.data.HourScheduleData;
import com.dromree.thermopi.rest.data.QuarterScheduleData;
import com.dromree.thermopi.rest.data.WeekScheduleData;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ScheduleConverter {

    // Begin Network to DB Conversions

    private QuarterSchedule convertQuarterNetworkToDBData(QuarterScheduleData networkData) {
        QuarterSchedule quarterSchedule = null;

        if(networkData != null) {
            quarterSchedule = new QuarterSchedule(networkData.getEnabled());
        }

        return quarterSchedule;
    }

    private Map<String, QuarterSchedule> convertQuarterNetworkToDBDataMap(Map<String, QuarterScheduleData> networkDataMap) {
        Map<String, QuarterSchedule> quarterScheduleMap = new HashMap<>();

        networkDataMap.forEach( (key, value) -> quarterScheduleMap.put(key, convertQuarterNetworkToDBData(value)));

        return quarterScheduleMap;
    }

    private HourSchedule convertHourNetworkToDBData(HourScheduleData networkData) {
        HourSchedule hourSchedule = null;

        if(networkData != null) {
            hourSchedule = new HourSchedule(convertQuarterNetworkToDBDataMap(networkData.getQuarters()));
        }

        return hourSchedule;
    }

    private Map<String, HourSchedule> convertHourNetworkToDBDataMap(Map<String, HourScheduleData> networkDataMap) {
        Map<String, HourSchedule> hourScheduleMap = new HashMap<>();

        networkDataMap.forEach( (key, value) -> hourScheduleMap.put(key, convertHourNetworkToDBData(value)));

        return hourScheduleMap;
    }

    public DaySchedule convertDayNetworkToDBData(DayScheduleData networkData) {
        DaySchedule daySchedule = null;

        if(networkData != null) {
            daySchedule = new DaySchedule(convertHourNetworkToDBDataMap(networkData.getHours()));
        }

        return daySchedule;
    }

    public Map<String, DaySchedule> convertDayNetworkToDBDataMap(Map<String, DayScheduleData> networkDataMap) {
        Map<String, DaySchedule> dayScheduleMap = new HashMap<>();

        networkDataMap.forEach( (key, value) -> dayScheduleMap.put(key, convertDayNetworkToDBData(value)));

        return dayScheduleMap;
    }

    public WeekSchedule convertWeekNetworkToDBData(WeekScheduleData networkData) {
        WeekSchedule weekSchedule = null;

        if(networkData != null) {
            weekSchedule = new WeekSchedule(networkData.getMonth(), convertDayNetworkToDBDataMap(networkData.getDays()));
        }

        return weekSchedule;
    }

    // End Network to DB conversions

    // Begin DB to Network Conversions

    private QuarterScheduleData convertQuarterDBToNetwork(QuarterSchedule dbData) {
        QuarterScheduleData quarterScheduleData = null;

        if(dbData != null) {
            quarterScheduleData = new QuarterScheduleData(dbData.getEnabled());
        }

        return quarterScheduleData;
    }

    private Map<String, QuarterScheduleData> convertQuarterDBToNetworkDataMap(Map<String, QuarterSchedule> dbDataMap) {
        Map<String, QuarterScheduleData> quarterScheduleDataMap = new HashMap<>();

        dbDataMap.forEach( (key, value) -> quarterScheduleDataMap.put(key, convertQuarterDBToNetwork(value)));

        return quarterScheduleDataMap;
    }

    private HourScheduleData convertHourNetworkToDBData(HourSchedule dbData) {
        HourScheduleData hourScheduleData = null;

        if(dbData != null) {
            hourScheduleData = new HourScheduleData(convertQuarterDBToNetworkDataMap(dbData.getQuarters()));
        }

        return hourScheduleData;
    }

    private Map<String, HourScheduleData> convertHourDBToNetworkDataMap(Map<String, HourSchedule> dbDataMap) {
        Map<String, HourScheduleData> hourScheduleDataMap = new HashMap<>();

        dbDataMap.forEach( (key, value) -> hourScheduleDataMap.put(key, convertHourNetworkToDBData(value)));

        return hourScheduleDataMap;
    }

    public DayScheduleData convertDayDBToNetworkData(DaySchedule dbData) {
        DayScheduleData dayScheduleData = null;

        if(dbData != null) {
            dayScheduleData = new DayScheduleData(convertHourDBToNetworkDataMap(dbData.getHours()));
        }

        return dayScheduleData;
    }

    private Map<String, DayScheduleData> convertDayDBToNetworkDataMap(Map<String, DaySchedule> dbDataMap) {
        Map<String, DayScheduleData> dayScheduleDataMap = new HashMap<>();

        dbDataMap.forEach( (key, value) -> dayScheduleDataMap.put(key, convertDayDBToNetworkData(value)));

        return dayScheduleDataMap;
    }

    public WeekScheduleData convertWeekDBToNetworkData(WeekSchedule dbData) {
        WeekScheduleData weekScheduleData = null;

        if(dbData != null) {
            weekScheduleData = new WeekScheduleData(dbData.getMonth(), convertDayDBToNetworkDataMap(dbData.getDays()));
        }

        return weekScheduleData;
    }

    // End DB to Network conversions - Move to converter class
}
