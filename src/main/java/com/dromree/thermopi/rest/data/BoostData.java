package com.dromree.thermopi.rest.data;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BoostData {

    private Boolean enabled;
    private Long endDate;

    public BoostData() {}

    public BoostData(Boolean enabled, Long endDate) {
        this.enabled = enabled;
        this.endDate = endDate;
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
}
