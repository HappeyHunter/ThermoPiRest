package com.dromree.thermopi.rest.endpoint;


import com.dromree.thermopi.services.TemperatureRecordService;
import com.dromree.thermopi.rest.data.TemperatureRecordData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Rest Controller for Current Temperature
 */
@RestController
@RequestMapping("/ThermoPi/CurrentTemperature")
public class CurrentTemperatureController {

    private static final Logger logger = LoggerFactory.getLogger(CurrentTemperatureController.class.getName());

    @Autowired
    private TemperatureRecordService temperatureRecordService;

    /**
     * Updates the current temperature with the provided value
     *
     * @param currentData   the new current temperature
     */
    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public void putCurrentTemperature(@RequestBody TemperatureRecordData currentData) {
        long startTime = System.currentTimeMillis();
        temperatureRecordService.recordCurrentTemperature(currentData);
        logger.debug("putCurrentTemperature: " + (System.currentTimeMillis()-startTime));
    }

    /**
     * Gets the current temperature
     *
     * @return  the current temperature
     */
    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public TemperatureRecordData getCurrentTemperature() {
        long startTime = System.currentTimeMillis();
        TemperatureRecordData temperatureRecordData = temperatureRecordService.getCurrentTemperature();
        logger.debug("getCurrentTemperature: " + (System.currentTimeMillis()-startTime));
        return temperatureRecordData;
    }
}
