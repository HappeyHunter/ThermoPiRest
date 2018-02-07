package com.dromree.thermopi.rest.endpoint;

import com.dromree.thermopi.rest.data.*;
import com.dromree.thermopi.services.BoostServices;
import com.dromree.thermopi.services.HeatingStatusServices;
import com.dromree.thermopi.services.TargetTemperatureService;
import com.dromree.thermopi.services.TemperatureRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Rest Controller for Current Status
 */
@RestController
@RequestMapping("/ThermoPi/CurrentStatus")
public class CurrentStatusController {

    private static final Logger logger = LoggerFactory.getLogger(CurrentStatusController.class.getName());

    @Autowired
    private HeatingStatusServices heatingStatusServices;

    @Autowired
    private BoostServices boostServices;

    @Autowired
    private TemperatureRecordService temperatureRecordService;

    @Autowired
    private TargetTemperatureService targetTemperatureService;

    /**
     * Gets the current status of the system.
     *
     * @return  Status containing the active status, boost setting, current temperature, and target temperature.
     */
    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public CurrentStatusData getStatus() {
        long startTime = System.currentTimeMillis();
        CurrentStatusData currentStatus = new CurrentStatusData();

        HeatingStatusData heatingStatusData = heatingStatusServices.getLatestHeatingStatus();

        BoostData boostData = boostServices.getLatestBoostSetting();
        TemperatureRecordData temperatureRecordData = temperatureRecordService.getCurrentTemperature();
        TargetTemperatureData targetTemperatureData = targetTemperatureService.getTargetTemperature();

        currentStatus.setHeatingEnabled(heatingStatusData.getEnabled());

        if(boostData != null && boostData.getEnabled() && boostData.getEndDate() > System.currentTimeMillis()) {
            currentStatus.setBoostEnabled(Boolean.TRUE);
        } else {
            currentStatus.setBoostEnabled(Boolean.FALSE);
        }

        currentStatus.setCurrentTemperature(temperatureRecordData.getTemperature());
        currentStatus.setTargetTemperature(targetTemperatureData.getTemperature());
        logger.debug("getStatus: " + (System.currentTimeMillis()-startTime));

        return currentStatus;
    }
}
