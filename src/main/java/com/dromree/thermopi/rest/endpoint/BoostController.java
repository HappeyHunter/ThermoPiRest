package com.dromree.thermopi.rest.endpoint;

import com.dromree.thermopi.services.BoostServices;
import com.dromree.thermopi.rest.data.BoostData;
import com.dromree.thermopi.rest.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Rest Controller for Boost
 */
@RestController
@RequestMapping("ThermoPi/Boost")
public class BoostController {

    private static final Logger logger = LoggerFactory.getLogger(BoostController.class.getName());

    @Autowired
    private BoostServices boostServices;

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
    public BoostData setBoostSetting(@RequestBody BoostData boostData) {
        long startTime = System.currentTimeMillis();
        boostData = boostServices.setBoostSetting(boostData);
        logger.debug("setBoostSetting: " + (System.currentTimeMillis()-startTime));

        return boostData;
    }

    /**
     * Gets the latest boost setting
     *
     * @return
     */
    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public BoostData getBoostSetting() {
        long startTime = System.currentTimeMillis();
        BoostData returnData = boostServices.getLatestBoostSetting();

        if(returnData == null) {
            throw new NotFoundException();
        }
        logger.debug("getBoostSetting: " + (System.currentTimeMillis()-startTime));

        return returnData;
    }
}
