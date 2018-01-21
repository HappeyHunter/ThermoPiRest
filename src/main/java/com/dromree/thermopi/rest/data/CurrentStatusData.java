package com.dromree.thermopi.rest.data;

public class CurrentStatusData {

    private Double currentTemperature;
    private Double targetTemperature;
    private Boolean heatingEnabled;
    private Boolean boostEnabled;

    public Double getCurrentTemperature() {
        return currentTemperature;
    }

    public void setCurrentTemperature(Double currentTemperature) {
        this.currentTemperature = currentTemperature;
    }

    public Double getTargetTemperature() {
        return targetTemperature;
    }

    public void setTargetTemperature(Double targetTemperature) {
        this.targetTemperature = targetTemperature;
    }

    public Boolean getHeatingEnabled() {
        return heatingEnabled;
    }

    public void setHeatingEnabled(Boolean heatingEnabled) {
        this.heatingEnabled = heatingEnabled;
    }

    public Boolean getBoostEnabled() {
        return boostEnabled;
    }

    public void setBoostEnabled(Boolean boostEnabled) {
        this.boostEnabled = boostEnabled;
    }
}
