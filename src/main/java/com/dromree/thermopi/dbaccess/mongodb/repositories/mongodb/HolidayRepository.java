package com.dromree.thermopi.dbaccess.mongodb.repositories.mongodb;

import com.dromree.thermopi.dbaccess.data.holidays.Holiday;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface HolidayRepository extends MongoRepository<Holiday, String> {

    public Holiday findByHolidayID(String holidayID);

    public List<Holiday> findHolidaysByEndDateGreaterThanOrderByStartDateAsc(Long date);

    public void deleteHolidayByHolidayID(String holidayID);

}
