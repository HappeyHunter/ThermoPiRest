package com.dromree.thermopi.services;

import com.dromree.thermopi.dbaccess.data.boost.Boost;
import com.dromree.thermopi.dbaccess.mongodb.repositories.mongodb.BoostRepository;
import com.dromree.thermopi.rest.data.BoostData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

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
     * @param boostData     The boost data to be saved
     * @return              The boost data with the end date added
     */
    public BoostData setBoostSetting(BoostData boostData) {
        if (boostData.getEnabled()) {
            // Trying to enable boost, add an end time
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.HOUR_OF_DAY, 1);
            Date endTime = cal.getTime();
            boostData.setEndDate(endTime);
        }

        Boost newBoost = new Boost(boostData.getEnabled(), boostData.getEndDate(), new Date());

        boostRepository.save(newBoost);

        return boostData;
    }
}
