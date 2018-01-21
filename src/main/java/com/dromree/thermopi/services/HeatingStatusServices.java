package com.dromree.thermopi.services;

import com.dromree.thermopi.dbaccess.data.heatingstatus.HeatingStatus;
import com.dromree.thermopi.dbaccess.mongodb.repositories.mongodb.HeatingStatusRepository;
import com.dromree.thermopi.rest.data.HeatingStatusData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HeatingStatusServices {

    private HeatingStatusRepository heatingStatusRepository;

    @Autowired
    public HeatingStatusServices(HeatingStatusRepository heatingStatusRepository) {
        this.heatingStatusRepository = heatingStatusRepository;
    }

    public HeatingStatusData getLatestHeatingStatus() {
        HeatingStatusData heatingStatusData = null;
        HeatingStatus heatingStatus = heatingStatusRepository.findTopByOrderByStatusTimeDesc();

        if(heatingStatus != null) {
            heatingStatusData = new HeatingStatusData(heatingStatus.getActive());
        }

        return heatingStatusData;
    }

    public void setHeatingStatus(HeatingStatusData heatingStatusData) {
        heatingStatusRepository.save(new HeatingStatus(heatingStatusData.getEnabled(), System.currentTimeMillis()));
    }
}
