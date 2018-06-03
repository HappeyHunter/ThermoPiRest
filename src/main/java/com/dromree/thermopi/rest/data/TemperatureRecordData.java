package com.dromree.thermopi.rest.data;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotNull;

/**
 * Network side data object for Temperature Record
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TemperatureRecordData {

    @NotNull(message = "Temperature is required for update")
    private Double temperature;
    private Double humidity;

    public TemperatureRecordData() {}

    public TemperatureRecordData(Double temperature, Double humidity) {
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
