package com.dromree.thermopi.rest.data;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * Network side data object for Holiday
 */
public class HolidayData {

    private String holidayID;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "UTC")
    private Date startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "UTC")
    private Date endDate;

    public HolidayData() {}

    public HolidayData(String holidayID, Date startDate, Date endDate) {
        this.holidayID = holidayID;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getHolidayID() {
        return holidayID;
    }

    public void setHolidayID(String iD) {
        this.holidayID = iD;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
