package com.dromree.thermopi.rest.data;

import com.dromree.thermopi.rest.validation.Quarters;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Map;

/**
 * Network side data object for Hour Schedule
 */
public class HourScheduleData {

    @NotEmpty(message = "Quarter schedule is required")
    @Size(min = 4, max = 4, message = "Quarter schedule must include settings for 4 quarters")
    private Map<@Quarters String, @Valid QuarterScheduleData> quarters;

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
