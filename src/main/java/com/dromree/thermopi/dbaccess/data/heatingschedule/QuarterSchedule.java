package com.dromree.thermopi.dbaccess.data.heatingschedule;

public class QuarterSchedule {

    private Boolean enabled;

    public QuarterSchedule() {}

    public QuarterSchedule(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

}
