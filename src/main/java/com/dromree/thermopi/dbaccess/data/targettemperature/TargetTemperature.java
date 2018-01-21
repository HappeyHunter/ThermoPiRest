package com.dromree.thermopi.dbaccess.data.targettemperature;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "TargetTemperature")
public class TargetTemperature {

    @Id
    private String id;

    private Double temperature;

    public TargetTemperature() {
    }

    public TargetTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }
}
