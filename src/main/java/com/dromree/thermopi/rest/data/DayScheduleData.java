package com.dromree.thermopi.rest.data;

import java.util.Map;

public class DayScheduleData {

    private Map<String, HourScheduleData> hours;

    public DayScheduleData() {}

    public DayScheduleData(Map<String, HourScheduleData> hours) {
        this.hours = hours;
    }

    public Map<String, HourScheduleData> getHours() {
        return hours;
    }

    public void setHours(Map<String, HourScheduleData> hours) {
        this.hours = hours;
    }
}
