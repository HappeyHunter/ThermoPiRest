package com.dromree.thermopi.rest.data;

public class HolidayData {

    private String holidayID;
    private Long startDate;
    private Long endDate;

    public HolidayData() {}

    public HolidayData(String holidayID, Long startDate, Long endDate) {
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
