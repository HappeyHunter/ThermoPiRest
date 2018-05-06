package com.dromree.thermopi.services;

import com.dromree.thermopi.dbaccess.data.holidays.Holiday;
import com.dromree.thermopi.dbaccess.mongodb.repositories.mongodb.HolidayRepository;
import com.dromree.thermopi.rest.data.HolidayData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Services for Holidays
 */
@Service
public class HolidayServices {

    private HolidayRepository holidayRepository;

    @Autowired
    public HolidayServices(HolidayRepository holidayRepository) {
        this.holidayRepository = holidayRepository;
    }

    private HolidayData convertDBToNetworkData(Holiday dbData) {
        HolidayData networkData = null;

        if(dbData != null) {
            networkData = new HolidayData(dbData.getHolidayID(), dbData.getStartDate(), dbData.getEndDate());
        }

        return networkData;
    }

    /**
     * Converts a list of db holidays to a list of network holidays
     *
     * @param dbList    List of holidays to be converted
     * @return          List of network holidays
     */
    private List<HolidayData> convertDBToNetworkDataList(List<Holiday> dbList) {
        List<HolidayData> networkList = new ArrayList<>();

        dbList.forEach((entry) -> networkList.add(convertDBToNetworkData(entry)));

        return networkList;
    }

    /**
     * Gets the holiday identified by the holiday id
     *
     * @param holidayID id of the holiday to be retrieved
     * @return          holiday identified by the provided if, null if it is not found
     */
    public HolidayData getHolidayByHolidayId(String holidayID) {
        Holiday holiday = holidayRepository.findByHolidayID(holidayID);

        return convertDBToNetworkData(holiday);
    }

    /**
     * Updates the holiday provided
     *
     * @param aHolidayData  holiday to update
     */
    public void updateHoliday(HolidayData aHolidayData) {
        Holiday holiday = holidayRepository.findByHolidayID(aHolidayData.getHolidayID());

        if(holiday != null) {
            holiday.setStartDate(aHolidayData.getStartDate());
            holiday.setEndDate(aHolidayData.getEndDate());
        } else {
            holiday = new Holiday(aHolidayData.getHolidayID(), aHolidayData.getStartDate(), aHolidayData.getEndDate());
        }

        holidayRepository.save(holiday);
    }

    /**
     * Adds the provided holiday to the database
     *
     * @param aHolidayData  holiday to be added
     */
    public void addHoliday(HolidayData aHolidayData) {
        Holiday holiday = new Holiday(aHolidayData.getHolidayID(), aHolidayData.getStartDate(), aHolidayData.getEndDate());

        holidayRepository.save(holiday);
    }

    /**
     * Gets a list of all current and future holidays
     *
     * @return list of current and future holidays
     */
    public List<HolidayData> getCurrentAndFutureHolidays() {
        List<HolidayData> returnData;

        List<Holiday> holidaysList = holidayRepository.findHolidaysByEndDateGreaterThanOrderByStartDateAsc(new Date());

        if(holidaysList.size() > 0) {
            returnData = convertDBToNetworkDataList(holidaysList);
        } else {
            returnData = new ArrayList<>();
        }

        return returnData;
    }

    /**
     * Deleted the holiday identified by the provided id from the database
     *
     * @param holidayID id of the holiday to be deleted
     */
    public void deleteHolidayByHolidayId(String holidayID) {
        holidayRepository.deleteHolidayByHolidayID(holidayID);
    }

    /**
     * Gets a count of the currently active holidays
     *
     * @return  Count of active holidays
     */
    public Long getCurrentHolidaysCount() {
        return holidayRepository.countHolidaysByStartDateLessThanEqualAndEndDateGreaterThanEqual(new Date(), new Date());
    }
}
