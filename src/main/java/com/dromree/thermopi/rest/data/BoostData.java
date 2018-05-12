package com.dromree.thermopi.rest.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;

/**
 * Network side data object for Boost
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BoostData {

    private Boolean enabled;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss:z")
    private LocalDateTime endDate;

    public BoostData() {}

    public BoostData(Boolean enabled, LocalDateTime endDate) {
        this.enabled = enabled;
        this.endDate = endDate;
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
}
