package com.dromree.thermopi.rest.data;

import com.dromree.thermopi.rest.validation.Days;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Map;

/**
 * Network side data object for Week Schedule
 */
public class WeekScheduleData {

    private Integer month;
    @NotEmpty(message = "Day schedule is required")
    @Size(min = 7, max = 7, message = "Schedule must include settings for 7 days")
    private Map<@Days String, @Valid DayScheduleData> days;

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
