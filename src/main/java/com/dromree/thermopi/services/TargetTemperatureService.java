package com.dromree.thermopi.services;

import com.dromree.thermopi.dbaccess.data.targettemperature.TargetTemperature;
import com.dromree.thermopi.dbaccess.mongodb.repositories.mongodb.TargetTemperatureRepository;
import com.dromree.thermopi.rest.data.TargetTemperatureData;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TargetTemperatureService {

    private TargetTemperatureRepository targetTemperatureRepository;

    public TargetTemperatureService(TargetTemperatureRepository targetTemperatureRepository) {
        this.targetTemperatureRepository = targetTemperatureRepository;
    }

    private TargetTemperatureData convertDBToNetworkData(TargetTemperature dbData) {
        TargetTemperatureData networkData = null;
        if(dbData != null) {
            networkData = new TargetTemperatureData(dbData.getTemperature());
        }

        return networkData;
    }

    public TargetTemperatureData getTargetTemperature() {
        List<TargetTemperature> targetTemperatureList = targetTemperatureRepository.findAll();
        TargetTemperatureData targetTemperatureData = null;

        if(targetTemperatureList.size() > 0) {
            targetTemperatureData = convertDBToNetworkData(targetTemperatureList.get(0));
        }

        return targetTemperatureData;
    }

    public void setTargetTemperature(TargetTemperatureData targetTemperatureData) {
        List<TargetTemperature> targetTemperatureList = targetTemperatureRepository.findAll();
        TargetTemperature targetTemperature;

        if(targetTemperatureList.size() > 0) {
            targetTemperature = targetTemperatureList.get(0);
            targetTemperature.setTemperature(targetTemperatureData.getTemperature());
        } else {
            targetTemperature = new TargetTemperature(targetTemperatureData.getTemperature());
        }

        targetTemperatureRepository.save(targetTemperature);

    }
}
