package com.dromree.thermopi.rest.data;

import javax.validation.constraints.PositiveOrZero;

/**
 * Network side data object for Target Temperature
 */
public class TargetTemperatureData {

    @PositiveOrZero(message = "Target temperature cannot be negative")
    private Double temperature;

    public TargetTemperatureData() {}

    public TargetTemperatureData(Double temperature) {
        this.temperature = temperature;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

}
