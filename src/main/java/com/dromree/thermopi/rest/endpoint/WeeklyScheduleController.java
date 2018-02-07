package com.dromree.thermopi.rest.endpoint;

import com.dromree.thermopi.services.HeatingScheduleServices;
import com.dromree.thermopi.rest.data.DayScheduleData;
import com.dromree.thermopi.rest.data.WeekScheduleData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Rest Controller for Weekly Schedule
 */
@RestController
@RequestMapping("/ThermoPi/WeeklySchedule")
public class WeeklyScheduleController {

    private static final Logger logger = LoggerFactory.getLogger(WeeklyScheduleController.class.getName());

    @Autowired
    private HeatingScheduleServices heatingScheduleServices;

    /**
     * Updates the identified month with the provided state
     *
     * @param month                 index of the month to be updated
     * @param weeklyScheduleData    new state of the month
     */
    @PutMapping(
            value = "/{month}",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public void putWeeklySchedule(@PathVariable("month") Integer month, @RequestBody WeekScheduleData weeklyScheduleData) {
        long startTime = System.currentTimeMillis();

        weeklyScheduleData.setMonth(month);

        heatingScheduleServices.updateHeatingScheduleByWeek(weeklyScheduleData);
        logger.debug("putWeeklySchedule: " + (System.currentTimeMillis()-startTime));

//        return Response.ok().build();
    }

    /**
     * Updates the identified day with the provided state
     *
     * @param month           index of the month to be updated
     * @param day             name of the day to be updated
     * @param dayScheduleData new state of the day
     */
    @PutMapping(
            value = "/{month}/{day}",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public void putDailySchedule(@PathVariable("month") Integer month, @PathVariable("day") String day, @RequestBody DayScheduleData dayScheduleData) {
        long startTime = System.currentTimeMillis();

        heatingScheduleServices.updateHeatingScheduleByDay(month, day, dayScheduleData);
        logger.debug("putDailySchedule: " + (System.currentTimeMillis()-startTime));

//        return Response.ok().build();
    }

    /**
     * Gets the month identified
     *
     * @param month index of the month to be rretireved
     * @return      data for the month provided
     */
    @GetMapping(
            value = "/{month}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WeekScheduleData getWeeklySchedule(@PathVariable("month") Integer month) {
        long startTime = System.currentTimeMillis();
        WeekScheduleData weekScheduleData = heatingScheduleServices.getScheduleByMonth(month);
        logger.debug("getWeeklySchedule: " + (System.currentTimeMillis()-startTime));

        return weekScheduleData;
    }

    /**
     * Gets the day identified
     *
     * @param month index of the month to be retrieved
     * @param day   name of the day to be retrieved
     * @return      data for the day provided
     */
    @GetMapping(
            value = "{month}/{day}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public DayScheduleData getDailySchedule(@PathVariable("month") Integer month, @PathVariable("day") String day) {
        long startTime = System.currentTimeMillis();
        DayScheduleData dayScheduleData = heatingScheduleServices.getScheduleByDay(month, day);
        logger.debug("getDailySchedule: " + (System.currentTimeMillis()-startTime));

        return dayScheduleData;
    }

}
