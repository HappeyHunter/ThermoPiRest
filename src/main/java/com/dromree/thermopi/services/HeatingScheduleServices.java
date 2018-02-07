package com.dromree.thermopi.services;

import com.dromree.thermopi.dbaccess.data.heatingschedule.WeekSchedule;
import com.dromree.thermopi.dbaccess.mongodb.repositories.mongodb.HeatingScheduleRepository;
import com.dromree.thermopi.rest.data.DayScheduleData;
import com.dromree.thermopi.rest.data.WeekScheduleData;
import com.dromree.thermopi.services.util.ScheduleConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Services for HeatingSchedule
 */
@Service
public class HeatingScheduleServices {

    private HeatingScheduleRepository heatingScheduleRepository;

    private ScheduleConverter scheduleConverter;

    @Autowired
    public HeatingScheduleServices(HeatingScheduleRepository heatingScheduleRepository, ScheduleConverter scheduleConverter) {
        this.heatingScheduleRepository = heatingScheduleRepository;
        this.scheduleConverter = scheduleConverter;
    }

    /**
     * Updates the schedule with the week provided.
     * The contains the index of the month that will be updated
     *
     * @param weeklyScheduleData    the new value for the week
     */
    public void updateHeatingScheduleByWeek(WeekScheduleData weeklyScheduleData) {
        WeekSchedule weekSchedule = heatingScheduleRepository.findWeekScheduleByMonth(weeklyScheduleData.getMonth());

        if(weekSchedule != null) {
            weekSchedule.setDays(scheduleConverter.convertDayNetworkToDBDataMap(weeklyScheduleData.getDays()));
        } else {
            weekSchedule = scheduleConverter.convertWeekNetworkToDBData(weeklyScheduleData);
        }

        heatingScheduleRepository.save(weekSchedule);
    }

    /**
     * Updates the schedule for the provided day
     *
     * @param month           The index of the month to be updated
     * @param day             The day to be updated
     * @param dayScheduleData The new value for the day
     */
    public void updateHeatingScheduleByDay(Integer month, String day, DayScheduleData dayScheduleData) {
        WeekSchedule weekSchedule = heatingScheduleRepository.findWeekScheduleByMonth(month);

        if(weekSchedule != null) {
            if(weekSchedule.getDays() != null) {
                weekSchedule.getDays().put(day, scheduleConverter.convertDayNetworkToDBData(dayScheduleData));
                heatingScheduleRepository.save(weekSchedule);
            }
        }

    }

    /**
     * Gets the schedule identified by the provided month
     *
     * @param month The index of the month to be retrieved
     * @return      The schedule data for the month
     */
    public WeekScheduleData getScheduleByMonth(Integer month) {
        WeekScheduleData returnData = null;

        WeekSchedule weekSchedule = heatingScheduleRepository.findWeekScheduleByMonth(month);

        if(weekSchedule != null) {
            returnData = scheduleConverter.convertWeekDBToNetworkData(weekSchedule);
        }

        return returnData;
    }

    /**
     * Gets the schedule data for provided day
     *
     * @param month The index of the month to be retrieved
     * @param day   The day to be retrieved
     * @return      The schedule data for the month
     */
    public DayScheduleData getScheduleByDay(Integer month, String day) {
        DayScheduleData returnData = null;
        WeekSchedule weekSchedule = heatingScheduleRepository.findWeekScheduleByMonth(month);

        if(weekSchedule != null) {
            if(weekSchedule.getDays() != null) {
                returnData = scheduleConverter.convertDayDBToNetworkData(weekSchedule.getDays().get(day));
            }
        }

        return returnData;
    }
}
