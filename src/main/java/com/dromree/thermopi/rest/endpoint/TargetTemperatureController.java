package com.dromree.thermopi.rest.endpoint;

import com.dromree.thermopi.services.TargetTemperatureService;
import com.dromree.thermopi.rest.data.TargetTemperatureData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Rest Controller for Target Temperature
 */
@RestController
@RequestMapping("/ThermoPi/TargetTemperature")
public class TargetTemperatureController {

    private static final Logger logger = LoggerFactory.getLogger(TargetTemperatureController.class.getName());

    @Autowired
    private TargetTemperatureService targetTemperatureService;

    /**
     * Update the target temperature to the provided value
     *
     * @param targetTemperatureData new target temperature
     */
    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public void putTargetTemperature(@RequestBody TargetTemperatureData targetTemperatureData) {
        long startTime = System.currentTimeMillis();
        targetTemperatureService.setTargetTemperature(targetTemperatureData);
        logger.debug("putTargetTemperature: " + (System.currentTimeMillis()-startTime));

//        return Response.ok().build();
    }

    /**
     * Gets the target temperature
     *
     * @return the target temperature
     */
    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public TargetTemperatureData getTargetTemperature() {
        long startTime = System.currentTimeMillis();
        TargetTemperatureData targetTemperatureData = targetTemperatureService.getTargetTemperature();
        logger.debug("getTargetTemperature: " + (System.currentTimeMillis()-startTime));

        return targetTemperatureData;
    }

}
