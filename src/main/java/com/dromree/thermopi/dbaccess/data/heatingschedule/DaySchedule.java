package com.dromree.thermopi.dbaccess.data.heatingschedule;

import java.util.Map;

public class DaySchedule {

    private Map<String, HourSchedule> hours;

    public DaySchedule() {}

    public DaySchedule(Map<String, HourSchedule> hours) {
        this.hours = hours;
    }

    public Map<String, HourSchedule> getHours() {
        return hours;
    }

    public void setHours(Map<String, HourSchedule> hours) {
        this.hours = hours;
    }
}
