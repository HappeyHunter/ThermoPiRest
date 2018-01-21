package com.dromree.thermopi.services;

import com.dromree.thermopi.dbaccess.data.holidays.Holiday;
import com.dromree.thermopi.dbaccess.mongodb.repositories.mongodb.HolidayRepository;
import com.dromree.thermopi.rest.data.HolidayData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    private List<HolidayData> convertDBToNetworkDataList(List<Holiday> dbList) {
        List<HolidayData> networkList = new ArrayList<>();

        dbList.forEach((entry) -> networkList.add(convertDBToNetworkData(entry)));

        return networkList;
    }

    public HolidayData getHolidayByHolidayId(String holidayID) {
        Holiday holiday = holidayRepository.findByHolidayID(holidayID);

        return convertDBToNetworkData(holiday);
    }

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

    public void addHoliday(HolidayData aHolidayData) {
        Holiday holiday = new Holiday(aHolidayData.getHolidayID(), aHolidayData.getStartDate(), aHolidayData.getEndDate());

        holidayRepository.save(holiday);
    }

    public List<HolidayData> getCurrentAndFutureHolidays() {
        List<HolidayData> returnData;

        List<Holiday> holidaysList = holidayRepository.findHolidaysByEndDateGreaterThanOrderByStartDateAsc(System.currentTimeMillis());

        if(holidaysList.size() > 0) {
            returnData = convertDBToNetworkDataList(holidaysList);
        } else {
            returnData = new ArrayList<>();
        }

        return returnData;
    }

    public void deleteHolidayByHolidayId(String holidayID) {
        holidayRepository.deleteHolidayByHolidayID(holidayID);
    }
}
