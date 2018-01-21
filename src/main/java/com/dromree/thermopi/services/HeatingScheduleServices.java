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

@Service
public class HeatingScheduleServices {

    private HeatingScheduleRepository heatingScheduleRepository;

    private ScheduleConverter scheduleConverter;

    @Autowired
    public HeatingScheduleServices(HeatingScheduleRepository heatingScheduleRepository, ScheduleConverter scheduleConverter) {
        this.heatingScheduleRepository = heatingScheduleRepository;
        this.scheduleConverter = scheduleConverter;
    }

    public void updateHeatingScheduleByWeek(WeekScheduleData weeklyScheduleData) {
        WeekSchedule weekSchedule = heatingScheduleRepository.findWeekScheduleByMonth(weeklyScheduleData.getMonth());

        if(weekSchedule != null) {
            weekSchedule.setDays(scheduleConverter.convertDayNetworkToDBDataMap(weeklyScheduleData.getDays()));
        } else {
            weekSchedule = scheduleConverter.convertWeekNetworkToDBData(weeklyScheduleData);
        }

        heatingScheduleRepository.save(weekSchedule);
    }

    public void updateHeatingScheduleByDay(Integer month, String day, DayScheduleData dayScheduleData) {
        WeekSchedule weekSchedule = heatingScheduleRepository.findWeekScheduleByMonth(month);

        if(weekSchedule != null) {
            if(weekSchedule.getDays() != null) {
                weekSchedule.getDays().put(day, scheduleConverter.convertDayNetworkToDBData(dayScheduleData));
                heatingScheduleRepository.save(weekSchedule);
            }
        }

    }

    public WeekScheduleData getScheduleByMonth(Integer month) {
        WeekScheduleData returnData = null;

        WeekSchedule weekSchedule = heatingScheduleRepository.findWeekScheduleByMonth(month);

        if(weekSchedule != null) {
            returnData = scheduleConverter.convertWeekDBToNetworkData(weekSchedule);
        }

        return returnData;
    }

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
