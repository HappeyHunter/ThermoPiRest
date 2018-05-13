package com.dromree.thermopi.boiler;

import com.dromree.thermopi.gpio.GPIOAccess;
import com.dromree.thermopi.rest.data.*;
import com.dromree.thermopi.services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Manages control of the boiler.
 * Periodically checks the status from the database to see if heating should be enabled or disabled
 *
 */
@Component
public class BoilerSignaler {

    private static final Logger logger = LoggerFactory.getLogger(BoilerSignaler.class.getName());

    private TargetTemperatureService targetTemperatureService;

    private TemperatureRecordService temperatureRecordService;

    private BoostServices boostServices;

    private HolidayServices holidayServices;

    private HeatingScheduleServices heatingScheduleServices;

    private HeatingStatusServices heatingStatusServices;

    private GPIOAccess gpio;

    @Autowired
    public BoilerSignaler(TargetTemperatureService targetTemperatureService,
                          TemperatureRecordService temperatureRecordService,
                          BoostServices boostServices,
                          HolidayServices holidayServices,
                          HeatingScheduleServices heatingScheduleServices,
                          HeatingStatusServices heatingStatusServices,
                          GPIOAccess gpio) {
        this.targetTemperatureService = targetTemperatureService;
        this.temperatureRecordService = temperatureRecordService;
        this.boostServices = boostServices;
        this.holidayServices = holidayServices;
        this.heatingScheduleServices = heatingScheduleServices;
        this.heatingStatusServices = heatingStatusServices;
        this.gpio = gpio;
    }


    /**
     * Checks if the heating should be enabled or disabled based on the state of the database
     *
     */
    @Scheduled(fixedRateString = "${boiler.updateinterval:120000}")
    public void updateActiveStatus() {
        logger.debug("Updating active status");

        boolean canBeEnabled;
        boolean enableHeating = false;

        // get temperatures - target/current
        TargetTemperatureData targetTemperatureData = targetTemperatureService.getTargetTemperature();

        TemperatureRecordData currentTemperature = temperatureRecordService.getCurrentTemperature();

        HeatingStatusData latestHeatingStatus = heatingStatusServices.getLatestHeatingStatus();

        boolean currentlyEnabled = latestHeatingStatus != null ? latestHeatingStatus.getEnabled() : false;

        if (targetTemperatureData != null && currentTemperature != null) {
            enableHeating = currentlyEnabled ?
                    currentTemperature.getTemperature() < targetTemperatureData.getTemperature() : currentTemperature.getTemperature() < (targetTemperatureData.getTemperature() - 2);
        }

        canBeEnabled = enableHeating && canActivateHeating();

        updateSignals(canBeEnabled, enableHeating);
    }

    /**
     * Updates the GPIO signals and adds the new status to the database
     *
     * @param canBeActive   if the heating can be active i.e. scheduled or boosted. Controls the valve.
     * @param enableHeating if the heating will be activated if allowed. Controls the boiler.
     */
    private void updateSignals(boolean canBeActive, boolean enableHeating) {
        HeatingStatusData heatingStatusData = new HeatingStatusData();

        if (canBeActive) {
            // set valve to active
            gpio.setValveState(true);

            logger.debug("Heating scheduled. Valve enabled");
            if (enableHeating) {
                // set heating to high
                logger.debug("Enable heating");
                heatingStatusData.setEnabled(true);
            } else {
                // set heating to low
                logger.debug("Disable heating");
                heatingStatusData.setEnabled(false);
            }
        } else {
            gpio.setValveState(false);

            // set valve to low
            logger.debug("Disable heating");
            heatingStatusData.setEnabled(false);
        }

        // update the signal
        gpio.setBoilerState(heatingStatusData.getEnabled());

        heatingStatusServices.setHeatingStatus(heatingStatusData);
    }

    /**
     * Returns true if the heating is either scheduled or boosted
     *
     * @return true if the heating is scheduled or boosted
     */
    private boolean canActivateHeating() {
        boolean canBeActive = false;

        // get boost
        BoostData currentBoostSetting = boostServices.getLatestBoostSetting();

        if (currentBoostSetting != null
                && currentBoostSetting.getEnabled() && LocalDateTime.now().isBefore(currentBoostSetting.getEndDate().toLocalDateTime())) {
            canBeActive = true;
        }

        if (!canBeActive) {
            // get holidays
            Long currentHolidaysCount = holidayServices.getCurrentHolidaysCount();

            if (currentHolidaysCount == 0) {
                // get schedule
                LocalDateTime now = LocalDateTime.now();
                DayScheduleData dayScheduleData = heatingScheduleServices.getScheduleByDay(now.getMonthValue(), now.getDayOfWeek().name());

                if (dayScheduleData != null && isScheduled(dayScheduleData)) {
                    canBeActive = true;
                }
            }
        }

        return canBeActive;
    }

    /**
     * Returns true if the heating is scheduled
     *
     * @param dayScheduleData   schedule data for the current day
     * @return                  true if the heating is scheduled for the current time in the day provided
     */
    private boolean isScheduled(DayScheduleData dayScheduleData) {
        LocalDateTime now = LocalDateTime.now();
        String hour = String.valueOf(now.getHour());

        return dayScheduleData.getHours().get(hour).getQuarters().get(getCurrentQuarter(now.getMinute())).getEnabled();
    }

    /**
     * Gets the name of the quarter the minute belongs to
     *
     * @param minute    the current minute of the hour
     * @return          the quarter the minute falls into
     */
    private String getCurrentQuarter(int minute) {
        return String.valueOf(minute / 15);
    }
}
