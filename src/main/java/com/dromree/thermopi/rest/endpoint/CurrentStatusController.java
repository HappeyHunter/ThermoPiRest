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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Rest Controller for Current Status
 */
@RestController
@RequestMapping("/ThermoPi/CurrentStatus")
public class CurrentStatusController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(CurrentStatusController.class.getName());

    private final HeatingStatusServices heatingStatusServices;

    private final BoostServices boostServices;

    private final TemperatureRecordService temperatureRecordService;

    private final TargetTemperatureService targetTemperatureService;

    @Autowired
    public CurrentStatusController(HeatingStatusServices heatingStatusServices, BoostServices boostServices, TemperatureRecordService temperatureRecordService, TargetTemperatureService targetTemperatureService) {
        this.heatingStatusServices = heatingStatusServices;
        this.boostServices = boostServices;
        this.temperatureRecordService = temperatureRecordService;
        this.targetTemperatureService = targetTemperatureService;
    }

    /**
     * Gets the current status of the system.
     *
     * @return  Status containing the active status, boost setting, current temperature, and target temperature.
     */
    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getStatus() {
        long startTime = System.currentTimeMillis();
        CurrentStatusData currentStatus = new CurrentStatusData();

        HeatingStatusData heatingStatusData = heatingStatusServices.getLatestHeatingStatus();

        BoostData boostData = boostServices.getLatestBoostSetting();
        TemperatureRecordData temperatureRecordData = temperatureRecordService.getCurrentTemperature();
        TargetTemperatureData targetTemperatureData = targetTemperatureService.getTargetTemperature();

        if(boostData == null || temperatureRecordData == null || targetTemperatureData == null) {
            return notFound();
        }

        currentStatus.setHeatingEnabled(heatingStatusData.getEnabled());

        if(boostData.getEnabled() && new Date().before(boostData.getEndDate())) {
            currentStatus.setBoostEnabled(Boolean.TRUE);
        } else {
            currentStatus.setBoostEnabled(Boolean.FALSE);
        }

        currentStatus.setCurrentTemperature(temperatureRecordData.getTemperature());
        currentStatus.setTargetTemperature(targetTemperatureData.getTemperature());
        logger.debug("getStatus: " + (System.currentTimeMillis()-startTime));

        return ok(currentStatus);
    }
}
