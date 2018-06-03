package com.dromree.thermopi.rest.data;

import javax.validation.constraints.NotNull;

/**
 * Network side data object for Quarter Schedule
 */
public class QuarterScheduleData {

    @NotNull(message = "Quarter state is required")
    private Boolean enabled;

    public QuarterScheduleData() {}

    public QuarterScheduleData(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
