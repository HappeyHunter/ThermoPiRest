package com.dromree.thermopi.dbaccess.data.holidays;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

/**
 * DB Entity for the Holidays
 */
@Document(collection = "Holidays")
public class Holiday {

    @Id
    private String id;

    @Indexed
    private String holidayID;
    private LocalDate startDate;
    @Indexed
    private LocalDate endDate;

    public Holiday() {}

    public Holiday(String holidayID, LocalDate startDate, LocalDate endDate) {
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
