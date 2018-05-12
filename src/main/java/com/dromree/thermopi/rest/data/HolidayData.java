package com.dromree.thermopi.rest.data;

import java.time.LocalDate;

/**
 * Network side data object for Holiday
 */
public class HolidayData {

    private String holidayID;
    private LocalDate startDate;
    private LocalDate endDate;

    public HolidayData() {}

    public HolidayData(String holidayID, LocalDate startDate, LocalDate endDate) {
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
