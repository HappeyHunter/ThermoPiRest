package com.dromree.thermopi.rest.endpoint;

import com.dromree.thermopi.rest.data.HolidayData;
import com.dromree.thermopi.services.HolidayServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

/**
 * Rest Controller for Holidays
 */
@RestController
@RequestMapping("/ThermoPi/Holidays")
public class HolidayController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(HolidayController.class.getName());

    private final HolidayServices holidayServices;

    public HolidayController(HolidayServices holidayServices) {
        this.holidayServices = holidayServices;
    }

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
    public ResponseEntity<?> putHoliday(@PathVariable("holidayID") String holidayID, @RequestBody @Valid HolidayData aHolidayData) {
        // Set the Id from the path
        aHolidayData.setHolidayID(holidayID);

        holidayServices.updateHoliday(aHolidayData);

        return ok();
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
    public ResponseEntity<?> getHoliday(@PathVariable("holidayID") String holidayID) {
        HolidayData holidayData = holidayServices.getHolidayByHolidayId(holidayID);

        if(holidayData == null) {
            return notFound();
        }

        return ok(holidayData);
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
    public ResponseEntity<?> addHoliday(@RequestBody @Valid HolidayData aHolidayData) {
        String holidayID = UUID.randomUUID().toString();
        aHolidayData.setHolidayID(holidayID);

        holidayServices.addHoliday(aHolidayData);

        return ok(aHolidayData);
    }

    /**
     * Gets a list of all current and future holidays
     *
     * @return  List of holidays or empty list if none are found.
     */
    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getHolidays() {
        List<HolidayData> holidayDataList = holidayServices.getCurrentAndFutureHolidays();

        return ok(holidayDataList);
    }

    /**
     * Delete the holiday identified by the id
     *
     * @param holidayID id of the holiday to be deleted
     */
    @DeleteMapping(
            value = "/{holidayID}"
    )
    public ResponseEntity<?> deleteHoliday(@PathVariable("holidayID") String holidayID) {
        holidayServices.deleteHolidayByHolidayId(holidayID);

        return ok();
    }
}
