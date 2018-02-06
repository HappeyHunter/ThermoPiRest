package com.dromree.thermopi.boiler;

import com.dromree.thermopi.gpio.GPIOAccess;
import com.dromree.thermopi.rest.data.*;
import com.dromree.thermopi.services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Component
public class BoilerSignaler {

    private static final Logger logger = LoggerFactory.getLogger(BoilerSignaler.class.getName());

    private String[] dayNames = {"", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

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

    @Scheduled(fixedRateString = "${boiler.updateinterval:300000}")
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

        canBeEnabled = canActivateHeating();

        updateSignals(canBeEnabled, enableHeating);
    }

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
            logger.debug("Heating not scheduled. Valve disabled");
            logger.debug("Disable heating");
            heatingStatusData.setEnabled(false);
        }

        // update the signal
        gpio.setBoilerState(heatingStatusData.getEnabled());

        heatingStatusServices.setHeatingStatus(heatingStatusData);
    }

    private boolean canActivateHeating() {
        boolean canBeActive = false;

        // get boost
        BoostData currentBoostSetting = boostServices.getLatestBoostSetting();

        if (currentBoostSetting != null
                && currentBoostSetting.getEnabled() && currentBoostSetting.getEndDate() > System.currentTimeMillis()) {
            canBeActive = true;
        }

        if (!canBeActive) {
            // get holidays
            Long currentHolidaysCount = holidayServices.getCurrentHolidaysCount();

            if (currentHolidaysCount == 0) {
                // get schedule
                Calendar cal = Calendar.getInstance();
                DayScheduleData dayScheduleData = heatingScheduleServices.getScheduleByDay(cal.get(Calendar.MONTH), dayNames[cal.get(Calendar.DAY_OF_WEEK)]);

                if (dayScheduleData != null && isScheduled(dayScheduleData)) {
                    canBeActive = true;
                }
            }
        }

        return canBeActive;
    }

    private boolean isScheduled(DayScheduleData dayScheduleData) {
        Calendar cal = Calendar.getInstance();
        String hour = String.valueOf(cal.get(Calendar.HOUR_OF_DAY));

        return dayScheduleData.getHours().get(hour).getQuarters().get(getCurrentQuarter(cal.get(Calendar.MINUTE))).getEnabled();
    }

    private String getCurrentQuarter(int minute) {
        return String.valueOf(minute / 15);
    }
}
