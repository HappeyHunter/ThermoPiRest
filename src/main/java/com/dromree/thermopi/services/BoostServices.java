package com.dromree.thermopi.services;

import com.dromree.thermopi.dbaccess.data.boost.Boost;
import com.dromree.thermopi.dbaccess.mongodb.repositories.mongodb.BoostRepository;
import com.dromree.thermopi.rest.data.BoostData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Services for Boost
 */
@Service
public class BoostServices {

    private BoostRepository boostRepository;

    @Autowired
    public BoostServices(BoostRepository boostRepository) {
        this.boostRepository = boostRepository;
    }

    /**
     * Gets the latest boost setting
     *
     * @return  The latest boost setting if found, null otherwise
     */
    public BoostData getLatestBoostSetting() {
        BoostData returnData = null;

        Boost boostData = boostRepository.findTopByOrderByTimestampDesc();

        if(boostData != null) {
            // Populate
            returnData = new BoostData(boostData.getEnabled(), boostData.getEndDate());
        }

        return returnData;
    }

    /**
     * Sets the boost setting and returns the new value.
     * Populates the end date if the boost is being enabled
     *
     * @param boostData
     * @return
     */
    public BoostData setBoostSetting(BoostData boostData) {
        if (boostData.getEnabled()) {
            // Trying to enable boost, add an end time
            Long endTime = System.currentTimeMillis() + 3600000; // Current time plus an hour
            boostData.setEndDate(endTime);
        }

        Boost newBoost = new Boost(boostData.getEnabled(), boostData.getEndDate(), System.currentTimeMillis());

        boostRepository.save(newBoost);

        return boostData;
    }
}
