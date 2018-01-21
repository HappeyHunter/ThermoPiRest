package com.dromree.thermopi.rest.endpoint;

import com.dromree.thermopi.services.BoostServices;
import com.dromree.thermopi.rest.annotation.Secure;
import com.dromree.thermopi.rest.data.BoostData;
import com.dromree.thermopi.rest.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("ThermoPi/Boost")
public class BoostController {

    private static final Logger logger = LoggerFactory.getLogger(BoostController.class.getName());

    @Autowired
    private BoostServices boostServices;

    @Secure
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

    @Secure
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
