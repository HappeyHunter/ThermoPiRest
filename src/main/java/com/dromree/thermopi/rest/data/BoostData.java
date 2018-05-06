package com.dromree.thermopi.rest.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

/**
 * Network side data object for Boost
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BoostData {

    private Boolean enabled;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss:z")
    private Date endDate;

    public BoostData() {}

    public BoostData(Boolean enabled, Date endDate) {
        this.enabled = enabled;
        this.endDate = endDate;
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
}
