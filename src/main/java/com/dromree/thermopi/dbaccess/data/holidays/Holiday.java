package com.dromree.thermopi.dbaccess.data.holidays;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * DB Entity for the Holidays
 */
@Document(collection = "Holidays")
public class Holiday {

    @Id
    private String id;

    @Indexed
    private String holidayID;
    private Long startDate;
    @Indexed
    private Long endDate;

    public Holiday() {}

    public Holiday(String holidayID, Long startDate, Long endDate) {
        this.holidayID = holidayID;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getHolidayID() {
        return holidayID;
    }

    public void setHolidayID(String holidayID) {
        this.holidayID = holidayID;
    }

    public Long getStartDate() {
        return startDate;
    }

    public void setStartDate(Long startDate) {
        this.startDate = startDate;
    }

    public Long getEndDate() {
        return endDate;
    }

    public void setEndDate(Long endDate) {
        this.endDate = endDate;
    }
}
