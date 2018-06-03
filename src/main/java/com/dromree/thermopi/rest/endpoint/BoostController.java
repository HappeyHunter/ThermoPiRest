package com.dromree.thermopi.rest.endpoint;

import com.dromree.thermopi.rest.data.BoostData;
import com.dromree.thermopi.services.BoostServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Rest Controller for Boost
 */
@RestController
@RequestMapping("ThermoPi/Boost")
public class BoostController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(BoostController.class.getName());

    private final BoostServices boostServices;

    public BoostController(BoostServices boostServices) {
        this.boostServices = boostServices;
    }

    /**
     * Updates the boost setting with the enabled state of the provided data and returns the new value.
     *
     * @param boostData A boost object with a valid enabled state
     * @return          The new boost setting with the end date populated if enabled
     */
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> setBoostSetting(@RequestBody @Valid BoostData boostData) {
        boostData = boostServices.setBoostSetting(boostData);

        return ok(boostData);
    }

    /**
     * Gets the latest boost setting
     *
     * @return  The latest boost setting
     */
    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getBoostSetting() {
        BoostData returnData = boostServices.getLatestBoostSetting();

        if(returnData == null) {
            return notFound();
        }

        return ok(returnData);
    }
}
