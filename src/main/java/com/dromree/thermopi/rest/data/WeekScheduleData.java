package com.dromree.thermopi.rest.data;

import java.util.Map;

public class WeekScheduleData {

    private Integer month;
    private Map<String, DayScheduleData> days;

    public WeekScheduleData() {}

    public WeekScheduleData(Integer month, Map<String, DayScheduleData> days) {
        this.month = month;
        this.days = days;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Map<String, DayScheduleData> getDays() {
        return days;
    }

    public void setDays(Map<String, DayScheduleData> days) {
        this.days = days;
    }
}
