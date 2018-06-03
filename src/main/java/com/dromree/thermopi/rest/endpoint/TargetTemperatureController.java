package com.dromree.thermopi.rest.endpoint;

import com.dromree.thermopi.rest.data.TargetTemperatureData;
import com.dromree.thermopi.services.TargetTemperatureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Rest Controller for Target Temperature
 */
@RestController
@RequestMapping("/ThermoPi/TargetTemperature")
public class TargetTemperatureController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(TargetTemperatureController.class.getName());

    private final TargetTemperatureService targetTemperatureService;

    public TargetTemperatureController(TargetTemperatureService targetTemperatureService) {
        this.targetTemperatureService = targetTemperatureService;
    }

    /**
     * Update the target temperature to the provided value
     *
     * @param targetTemperatureData new target temperature
     */
    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> putTargetTemperature(@RequestBody @Valid TargetTemperatureData targetTemperatureData) {
        targetTemperatureService.setTargetTemperature(targetTemperatureData);

        return ok();
    }

    /**
     * Gets the target temperature
     *
     * @return the target temperature
     */
    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getTargetTemperature() {
        TargetTemperatureData targetTemperatureData = targetTemperatureService.getTargetTemperature();

        return ok(targetTemperatureData);
    }

}
