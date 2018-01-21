package com.dromree.thermopi.rest.endpoint;

import com.dromree.thermopi.services.HolidayServices;
import com.dromree.thermopi.rest.annotation.Secure;
import com.dromree.thermopi.rest.data.HolidayData;
import com.dromree.thermopi.rest.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Secure
@RestController
@RequestMapping("/ThermoPi/Holidays")
public class HolidayController {

    private static final Logger logger = LoggerFactory.getLogger(HolidayController.class.getName());

    @Autowired
    private HolidayServices holidayServices;

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

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<HolidayData> getHolidays() {
        long startTime = System.currentTimeMillis();
        List<HolidayData> holidayDataList = holidayServices.getCurrentAndFutureHolidays();
        logger.debug("getHolidays: " + (System.currentTimeMillis()-startTime));
        return holidayDataList;
    }

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
