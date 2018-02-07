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

/**
 * Converts schedule data between network and db objects
 */
@Service
public class ScheduleConverter {

    // Begin Network to DB Conversions

    /**
     * Converts a network quarter to a db quarter
     *
     * @param networkData   network quarter to be converted
     * @return              db quarter
     */
    private QuarterSchedule convertQuarterNetworkToDBData(QuarterScheduleData networkData) {
        QuarterSchedule quarterSchedule = null;

        if(networkData != null) {
            quarterSchedule = new QuarterSchedule(networkData.getEnabled());
        }

        return quarterSchedule;
    }

    /**
     * Converts a map of network quarters to db quarters
     *
     * @param networkDataMap    map of network quarters to be converted
     * @return                  map of db quarters
     */
    private Map<String, QuarterSchedule> convertQuarterNetworkToDBDataMap(Map<String, QuarterScheduleData> networkDataMap) {
        Map<String, QuarterSchedule> quarterScheduleMap = new HashMap<>();

        networkDataMap.forEach( (key, value) -> quarterScheduleMap.put(key, convertQuarterNetworkToDBData(value)));

        return quarterScheduleMap;
    }

    /**
     * Converts a network hour to a db hour
     *
     * @param networkData   network hour to be converted
     * @return              db hour
     */
    private HourSchedule convertHourDBToNetworkData(HourScheduleData networkData) {
        HourSchedule hourSchedule = null;

        if(networkData != null) {
            hourSchedule = new HourSchedule(convertQuarterNetworkToDBDataMap(networkData.getQuarters()));
        }

        return hourSchedule;
    }

    /**
     * Converts a map of network hours to db hours
     *
     * @param networkDataMap    map of network hours
     * @return                  map of db hours
     */
    private Map<String, HourSchedule> convertHourNetworkToDBDataMap(Map<String, HourScheduleData> networkDataMap) {
        Map<String, HourSchedule> hourScheduleMap = new HashMap<>();

        networkDataMap.forEach( (key, value) -> hourScheduleMap.put(key, convertHourDBToNetworkData(value)));

        return hourScheduleMap;
    }

    /**
     * Converts a network day into a db day
     *
     * @param networkData   network day to be converted
     * @return              db day
     */
    public DaySchedule convertDayNetworkToDBData(DayScheduleData networkData) {
        DaySchedule daySchedule = null;

        if(networkData != null) {
            daySchedule = new DaySchedule(convertHourNetworkToDBDataMap(networkData.getHours()));
        }

        return daySchedule;
    }

    /**
     * Converts a map of network days into a map of db days
     *
     * @param networkDataMap    map of network days
     * @return                  map of db days
     */
    public Map<String, DaySchedule> convertDayNetworkToDBDataMap(Map<String, DayScheduleData> networkDataMap) {
        Map<String, DaySchedule> dayScheduleMap = new HashMap<>();

        networkDataMap.forEach( (key, value) -> dayScheduleMap.put(key, convertDayNetworkToDBData(value)));

        return dayScheduleMap;
    }

    /**
     * Converts a network week into a db week
     *
     * @param networkData   network week to be converted
     * @return              db week
     */
    public WeekSchedule convertWeekNetworkToDBData(WeekScheduleData networkData) {
        WeekSchedule weekSchedule = null;

        if(networkData != null) {
            weekSchedule = new WeekSchedule(networkData.getMonth(), convertDayNetworkToDBDataMap(networkData.getDays()));
        }

        return weekSchedule;
    }

    // End Network to DB conversions

    // Begin DB to Network Conversions

    /**
     * Converts a db quarter to a network quarter
     *
     * @param dbData    db quarter to be converted
     * @return          network quarter
     */
    private QuarterScheduleData convertQuarterDBToNetwork(QuarterSchedule dbData) {
        QuarterScheduleData quarterScheduleData = null;

        if(dbData != null) {
            quarterScheduleData = new QuarterScheduleData(dbData.getEnabled());
        }

        return quarterScheduleData;
    }

    /**
     * Converts a map of db quarters to a map of network quarters
     *
     * @param dbDataMap map of db quarters to be converted
     * @return          map of network quarters
     */
    private Map<String, QuarterScheduleData> convertQuarterDBToNetworkDataMap(Map<String, QuarterSchedule> dbDataMap) {
        Map<String, QuarterScheduleData> quarterScheduleDataMap = new HashMap<>();

        dbDataMap.forEach( (key, value) -> quarterScheduleDataMap.put(key, convertQuarterDBToNetwork(value)));

        return quarterScheduleDataMap;
    }

    /**
     * Converts a db hour to a network hour
     *
     * @param dbData    db hour to be converted
     * @return          network hour
     */
    private HourScheduleData convertHourDBToNetworkData(HourSchedule dbData) {
        HourScheduleData hourScheduleData = null;

        if(dbData != null) {
            hourScheduleData = new HourScheduleData(convertQuarterDBToNetworkDataMap(dbData.getQuarters()));
        }

        return hourScheduleData;
    }

    /**
     * Converts a map of db hours to a map of network hours
     *
     * @param dbDataMap map of db hours to be converted
     * @return          map of network hours
     */
    private Map<String, HourScheduleData> convertHourDBToNetworkDataMap(Map<String, HourSchedule> dbDataMap) {
        Map<String, HourScheduleData> hourScheduleDataMap = new HashMap<>();

        dbDataMap.forEach( (key, value) -> hourScheduleDataMap.put(key, convertHourDBToNetworkData(value)));

        return hourScheduleDataMap;
    }

    /**
     * Converts a db day to a network day
     *
     * @param dbData    db day to be converted
     * @return          network day
     */
    public DayScheduleData convertDayDBToNetworkData(DaySchedule dbData) {
        DayScheduleData dayScheduleData = null;

        if(dbData != null) {
            dayScheduleData = new DayScheduleData(convertHourDBToNetworkDataMap(dbData.getHours()));
        }

        return dayScheduleData;
    }

    /**
     * Converts a map of db days to a map of network days
     *
     * @param dbDataMap map of db days to be converted
     * @return          map of network days
     */
    private Map<String, DayScheduleData> convertDayDBToNetworkDataMap(Map<String, DaySchedule> dbDataMap) {
        Map<String, DayScheduleData> dayScheduleDataMap = new HashMap<>();

        dbDataMap.forEach( (key, value) -> dayScheduleDataMap.put(key, convertDayDBToNetworkData(value)));

        return dayScheduleDataMap;
    }

    /**
     * Converts a db week to a network week
     *
     * @param dbData    db week to be converted
     * @return          network week
     */
    public WeekScheduleData convertWeekDBToNetworkData(WeekSchedule dbData) {
        WeekScheduleData weekScheduleData = null;

        if(dbData != null) {
            weekScheduleData = new WeekScheduleData(dbData.getMonth(), convertDayDBToNetworkDataMap(dbData.getDays()));
        }

        return weekScheduleData;
    }

    // End DB to Network conversions - Move to converter class
}
