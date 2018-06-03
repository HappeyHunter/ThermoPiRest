package com.dromree.thermopi.rest.data;

import com.dromree.thermopi.rest.validation.HolidayDate;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Network side data object for Holiday
 */
@HolidayDate
public class HolidayData {

    private String holidayID;
    @NotNull(message = "Start Date is required")
    @FutureOrPresent(message = "Start date must be today or in the future")
    private LocalDate startDate;
    @NotNull(message = "End Date is required")
    @FutureOrPresent(message = "End date must be today or in the future")
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
