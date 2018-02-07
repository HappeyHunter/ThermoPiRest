package com.dromree.thermopi.dbaccess.data.temperaturelog;

/**
 * DB Entity for the SensorReading of the TemperatureLog
 */
public class SensorReading {

    private Double temperature;
    private Double humidity;

    public SensorReading() {
    }

    public SensorReading(Double temperature, Double humidity) {
        this.temperature = temperature;
        this.humidity = humidity;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }
}
