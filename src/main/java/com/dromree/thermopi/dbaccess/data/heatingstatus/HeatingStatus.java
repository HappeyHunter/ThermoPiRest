package com.dromree.thermopi.dbaccess.data.heatingstatus;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * DB Entity for the HeatingStatus
 */
@Document(collection = "HeatingStatus")
public class HeatingStatus {

    @Id
    private String id;

    private Boolean active;
    @Indexed
    private Date statusTime;

    public HeatingStatus() {}

    public HeatingStatus(Boolean active, Date statusTime) {
        this.statusTime = statusTime;
        this.active = active;
    }

    public Date getStatusTime() {
        return statusTime;
    }

    public void setStatusTime(Date statusTime) {
        this.statusTime = statusTime;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
