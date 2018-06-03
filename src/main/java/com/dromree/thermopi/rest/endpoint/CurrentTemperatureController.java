package com.dromree.thermopi.rest.endpoint;


import com.dromree.thermopi.rest.data.TemperatureRecordData;
import com.dromree.thermopi.services.TemperatureRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Rest Controller for Current Temperature
 */
@RestController
@RequestMapping("/ThermoPi/CurrentTemperature")
public class CurrentTemperatureController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(CurrentTemperatureController.class.getName());

    private final TemperatureRecordService temperatureRecordService;

    public CurrentTemperatureController(TemperatureRecordService temperatureRecordService) {
        this.temperatureRecordService = temperatureRecordService;
    }

    /**
     * Updates the current temperature with the provided value
     *
     * @param currentData   the new current temperature
     */
    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> putCurrentTemperature(@RequestBody @Valid TemperatureRecordData currentData) {
        temperatureRecordService.recordCurrentTemperature(currentData);

        return ok();
    }

    /**
     * Gets the current temperature
     *
     * @return  the current temperature
     */
    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getCurrentTemperature() {
        TemperatureRecordData temperatureRecordData = temperatureRecordService.getCurrentTemperature();

        return ok(temperatureRecordData);
    }
}
