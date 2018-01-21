package com.dromree.thermopi.dbaccess.data.boost;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "BoostSetting")
public class Boost {

    @Id
    public String id;

    private Boolean enabled;
    private Long endDate;
    @Indexed
    private Long timestamp;

    public Boost() {}

    public Boost(Boolean enabled, Long endDate, Long timestamp) {
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

    public Long getEndDate() {
        return endDate;
    }

    public void setEndDate(Long endDate) {
        this.endDate = endDate;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
