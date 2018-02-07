package com.dromree.thermopi.dbaccess.data.heatingstatus;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * DB Entity for the HeatingStatus
 */
@Document(collection = "HeatingStatus")
public class HeatingStatus {

    @Id
    private String id;

    private Boolean active;
    @Indexed
    private Long statusTime;

    public HeatingStatus() {}

    public HeatingStatus(Boolean active, Long statusTime) {
        this.statusTime = statusTime;
        this.active = active;
    }

    public Long getStatusTime() {
        return statusTime;
    }

    public void setStatusTime(Long statusTime) {
        this.statusTime = statusTime;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
