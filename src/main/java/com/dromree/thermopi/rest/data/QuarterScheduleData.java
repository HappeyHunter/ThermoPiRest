package com.dromree.thermopi.rest.data;

/**
 * Network side data object for Quarter Schedule
 */
public class QuarterScheduleData {

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
