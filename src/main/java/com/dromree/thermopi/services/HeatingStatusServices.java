package com.dromree.thermopi.services;

import com.dromree.thermopi.dbaccess.data.heatingstatus.HeatingStatus;
import com.dromree.thermopi.dbaccess.mongodb.repositories.mongodb.HeatingStatusRepository;
import com.dromree.thermopi.rest.data.HeatingStatusData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Services for the HeatingStatus
 */
@Service
public class HeatingStatusServices {

    private HeatingStatusRepository heatingStatusRepository;

    @Autowired
    public HeatingStatusServices(HeatingStatusRepository heatingStatusRepository) {
        this.heatingStatusRepository = heatingStatusRepository;
    }

    /**
     * Gets the latest status of the heating
     *
     * @return  latest heating status
     */
    public HeatingStatusData getLatestHeatingStatus() {
        HeatingStatusData heatingStatusData = null;
        HeatingStatus heatingStatus = heatingStatusRepository.findTopByOrderByStatusTimeDesc();

        if(heatingStatus != null) {
            heatingStatusData = new HeatingStatusData(heatingStatus.getActive());
        }

        return heatingStatusData;
    }

    /**
     * Saves the heating status provided in the database.
     * Populates with the current millis time.
     *
     * @param heatingStatusData HeatingStatus to be added
     */
    public void setHeatingStatus(HeatingStatusData heatingStatusData) {
        heatingStatusRepository.save(new HeatingStatus(heatingStatusData.getEnabled(), System.currentTimeMillis()));
    }
}
