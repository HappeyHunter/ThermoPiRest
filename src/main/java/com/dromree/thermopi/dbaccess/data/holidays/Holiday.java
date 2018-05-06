package com.dromree.thermopi.dbaccess.data.holidays;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * DB Entity for the Holidays
 */
@Document(collection = "Holidays")
public class Holiday {

    @Id
    private String id;

    @Indexed
    private String holidayID;
    private Date startDate;
    @Indexed
    private Date endDate;

    public Holiday() {}

    public Holiday(String holidayID, Date startDate, Date endDate) {
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
