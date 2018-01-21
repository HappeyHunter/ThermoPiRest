package com.dromree.thermopi.dbaccess.data.heatingschedule;

import java.util.Map;

public class HourSchedule {

    private Map<String, QuarterSchedule> quarters;

    public HourSchedule() {}

    public HourSchedule(Map<String, QuarterSchedule> quarters) {
        this.quarters = quarters;
    }

    public Map<String, QuarterSchedule> getQuarters() {
        return quarters;
    }

    public void setQuarters(Map<String, QuarterSchedule> quarters) {
        this.quarters = quarters;
    }
}
