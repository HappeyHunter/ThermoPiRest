package com.dromree.thermopi.rest.data;

/**
 * Network side data object for Target Temperature
 */
public class TargetTemperatureData {

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
