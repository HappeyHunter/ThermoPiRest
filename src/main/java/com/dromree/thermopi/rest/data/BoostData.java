package com.dromree.thermopi.rest.data;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.ZonedDateTime;

/**
 * Network side data object for Boost
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BoostData {

    private Boolean enabled;
    private ZonedDateTime endDate;

    public BoostData() {}

    public BoostData(Boolean enabled, ZonedDateTime endDate) {
        this.enabled = enabled;
        this.endDate = endDate;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public ZonedDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(ZonedDateTime endDate) {
        this.endDate = endDate;
    }
}
