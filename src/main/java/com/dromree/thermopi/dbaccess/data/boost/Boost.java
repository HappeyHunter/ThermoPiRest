package com.dromree.thermopi.dbaccess.data.boost;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * DB Entity for Boost
 *
 */
@Document(collection = "BoostSetting")
public class Boost {

    @Id
    public String id;

    private Boolean enabled;
    private LocalDateTime endDate;
    @Indexed
    private LocalDateTime timestamp;

    public Boost() {}

    public Boost(Boolean enabled, LocalDateTime endDate, LocalDateTime timestamp) {
        this.enabled = enabled;
        this.endDate = endDate;
        this.timestamp = timestamp;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
