package com.dromree.thermopi.dbaccess.data.heatingschedule;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document(collection = "HeatingSchedule")
public class WeekSchedule {

    @Id
    private String id;

    @Indexed
    private Integer month;
    private Map<String, DaySchedule> days;

    public WeekSchedule() {}

    public WeekSchedule(Integer month, Map<String, DaySchedule> days) {
        this.month = month;
        this.days = days;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Map<String, DaySchedule> getDays() {
        return days;
    }

    public void setDays(Map<String, DaySchedule> days) {
        this.days = days;
    }

}
