package com.dromree.thermopi.dbaccess.data.heatingstatus;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * DB Entity for the HeatingStatus
 */
@Document(collection = "HeatingStatus")
public class HeatingStatus {

    @Id
    private String id;

    private Boolean active;
    @Indexed
    private LocalDateTime statusTime;

    public HeatingStatus() {}

    public HeatingStatus(Boolean active, LocalDateTime statusTime) {
        this.statusTime = statusTime;
        this.active = active;
    }

    public LocalDateTime getStatusTime() {
        return statusTime;
    }

    public void setStatusTime(LocalDateTime statusTime) {
        this.statusTime = statusTime;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
