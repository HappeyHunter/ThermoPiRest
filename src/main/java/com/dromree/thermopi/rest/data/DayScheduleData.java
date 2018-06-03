package com.dromree.thermopi.rest.data;

import com.dromree.thermopi.rest.validation.Hours;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Map;

/**
 * Network side data object for Day Schedule
 */
public class DayScheduleData {

    @NotEmpty(message = "Hour schedule is required")
    @Size(min = 24, max = 24, message = "Schedule must include settings for 24 hours")
    private Map<@Hours String, @Valid HourScheduleData> hours;

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
