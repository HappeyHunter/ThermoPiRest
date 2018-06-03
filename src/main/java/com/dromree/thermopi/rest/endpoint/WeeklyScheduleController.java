package com.dromree.thermopi.rest.endpoint;

import com.dromree.thermopi.rest.data.DayScheduleData;
import com.dromree.thermopi.rest.data.WeekScheduleData;
import com.dromree.thermopi.services.HeatingScheduleServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Rest Controller for Weekly Schedule
 */
@RestController
@RequestMapping("/ThermoPi/WeeklySchedule")
public class WeeklyScheduleController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(WeeklyScheduleController.class.getName());

    private final HeatingScheduleServices heatingScheduleServices;

    public WeeklyScheduleController(HeatingScheduleServices heatingScheduleServices) {
        this.heatingScheduleServices = heatingScheduleServices;
    }

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
    public ResponseEntity<?> putWeeklySchedule(@PathVariable("month") Integer month, @RequestBody @Valid WeekScheduleData weeklyScheduleData) {
        weeklyScheduleData.setMonth(month);

        return heatingScheduleServices.updateHeatingScheduleByWeek(weeklyScheduleData)
            ? ok()
            : notFound();
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
    public ResponseEntity<?> putDailySchedule(@PathVariable("month") Integer month, @PathVariable("day") String day, @RequestBody @Valid DayScheduleData dayScheduleData) {
        return heatingScheduleServices.updateHeatingScheduleByDay(month, day, dayScheduleData)
            ? ok()
            : notFound();
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
    public ResponseEntity<?> getWeeklySchedule(@PathVariable("month") Integer month) {
        WeekScheduleData weekScheduleData = heatingScheduleServices.getScheduleByMonth(month);

        return weekScheduleData != null
            ? ok(weekScheduleData)
            : notFound();
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
    public ResponseEntity<?> getDailySchedule(@PathVariable("month") Integer month, @PathVariable("day") String day) {
        DayScheduleData dayScheduleData = heatingScheduleServices.getScheduleByDay(month, day);

        return dayScheduleData != null
            ? ok(dayScheduleData)
            : notFound();
    }

}
