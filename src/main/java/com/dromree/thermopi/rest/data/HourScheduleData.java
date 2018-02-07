package com.dromree.thermopi.rest.data;

import java.util.Map;

/**
 * Network side data object for Hour Schedule
 */
public class HourScheduleData {

    private Map<String, QuarterScheduleData> quarters;

    public HourScheduleData() {}

    public HourScheduleData(Map<String, QuarterScheduleData> quarters) {
        this.quarters = quarters;
    }

    public Map<String, QuarterScheduleData> getQuarters() {
        return quarters;
    }

    public void setQuarters(Map<String, QuarterScheduleData> quarters) {
        this.quarters = quarters;
    }
}
