package com.dromree.thermopi.dbaccess.data.boost;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * DB Entity for Boost
 *
 */
@Document(collection = "BoostSetting")
public class Boost {

    @Id
    public String id;

    private Boolean enabled;
    private Date endDate;
    @Indexed
    private Date timestamp;

    public Boost() {}

    public Boost(Boolean enabled, Date endDate, Date timestamp) {
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

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
