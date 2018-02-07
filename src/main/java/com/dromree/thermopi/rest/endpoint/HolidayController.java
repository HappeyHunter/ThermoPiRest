package com.dromree.thermopi.rest.endpoint;

import com.dromree.thermopi.services.HolidayServices;
import com.dromree.thermopi.rest.data.HolidayData;
import com.dromree.thermopi.rest.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Rest Controller for Holidays
 */
@RestController
@RequestMapping("/ThermoPi/Holidays")
public class HolidayController {

    private static final Logger logger = LoggerFactory.getLogger(HolidayController.class.getName());

    @Autowired
    private HolidayServices holidayServices;

    /**
     * Updates the holiday identified by the id
     *
     * @param holidayID     id of the holiday to be updated
     * @param aHolidayData  new state of the holiday
     */
    @PutMapping(
            value = "/{holidayID}",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public void putHoliday(@PathVariable("holidayID") String holidayID, @RequestBody HolidayData aHolidayData) {
        long startTime = System.currentTimeMillis();
        // Set the Id from the path
        aHolidayData.setHolidayID(holidayID);

        holidayServices.updateHoliday(aHolidayData);
        logger.debug("putHoliday: " + (System.currentTimeMillis()-startTime));
    }

    /**
     * Gets the holiday identified by the provided id
     *
     * @param holidayID id of the holiday to be retrieved
     * @return  The holiday with the provided id if found
     */
    @GetMapping(
            value = "/{holidayID}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public HolidayData getHoliday(@PathVariable("holidayID") String holidayID) {
        long startTime = System.currentTimeMillis();
        HolidayData holidayData = holidayServices.getHolidayByHolidayId(holidayID);

        if(holidayData == null) {
            throw new NotFoundException();
        }
        logger.debug("getHoliday: " + (System.currentTimeMillis()-startTime));

        return holidayData;
    }

    /**
     * Adds a new holiday using the provided holiday.
     * Generates a new id and returns the holiday with the id
     *
     * @param aHolidayData  new holiday to be added
     * @return              the holiday added and its generated id
     */
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public HolidayData addHoliday(@RequestBody HolidayData aHolidayData) {
        long startTime = System.currentTimeMillis();
        String holidayID = UUID.randomUUID().toString();
        aHolidayData.setHolidayID(holidayID);

        holidayServices.addHoliday(aHolidayData);
        logger.debug("addHoliday: " + (System.currentTimeMillis()-startTime));

        return aHolidayData;
    }

    /**
     * Gets a list of all current and future holidays
     *
     * @return  List of holidays or empty list if none are found.
     */
    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<HolidayData> getHolidays() {
        long startTime = System.currentTimeMillis();
        List<HolidayData> holidayDataList = holidayServices.getCurrentAndFutureHolidays();
        logger.debug("getHolidays: " + (System.currentTimeMillis()-startTime));
        return holidayDataList;
    }

    /**
     * Delete the holiday identified by the id
     *
     * @param holidayID id of the holiday to be deleted
     */
    @DeleteMapping(
            value = "/{holidayID}"
    )
    public void deleteHoliday(@PathVariable("holidayID") String holidayID) {
        long startTime = System.currentTimeMillis();
        holidayServices.deleteHolidayByHolidayId(holidayID);
        logger.debug("deleteHoliday: " + (System.currentTimeMillis()-startTime));

//        return Response.ok().build();
    }
}
