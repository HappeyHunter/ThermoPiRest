package com.dromree.thermopi.rest.data;

/**
 * Network side data object for Heating Status
 */
public class HeatingStatusData {

    private Boolean enabled;

    public HeatingStatusData() {}

    public HeatingStatusData(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
