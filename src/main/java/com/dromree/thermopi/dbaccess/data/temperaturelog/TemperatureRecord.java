package com.dromree.thermopi.dbaccess.data.temperaturelog;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

/**
 * DB Entity for the TemperatureLog
 */
@Document(collection = "TemperatureLog")
@CompoundIndexes({
        @CompoundIndex(name = "year_month_day", def = "{'year' : -1, 'month' : -1, 'day' : -1}")
})
public class TemperatureRecord {

    @Id
    private String id;

    private Integer year;
    private Integer month;
    private Integer day;
    private SensorReading lastReading;
    private Map<Integer, SensorReading> minutes;

    public TemperatureRecord() {}

    public TemperatureRecord(Integer year, Integer month, Integer day, SensorReading lastReading, Map<Integer, SensorReading> minutes) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.lastReading = lastReading;
        this.minutes = minutes;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Map<Integer, SensorReading> getMinutes() {
        return minutes;
    }

    public void setMinutes(Map<Integer, SensorReading> minutes) {
        this.minutes = minutes;
    }

    public SensorReading getLastReading() {
        return lastReading;
    }

    public void setLastReading(SensorReading lastReading) {
        this.lastReading = lastReading;
    }

    public String getId() {
        return id;
    }
}
