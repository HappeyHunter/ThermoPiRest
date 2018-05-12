package com.dromree.thermopi.dbaccess.mongodb.repositories.mongodb;

import com.dromree.thermopi.dbaccess.data.holidays.Holiday;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HolidayRepository extends MongoRepository<Holiday, String> {

    Holiday findByHolidayID(String holidayID);

    List<Holiday> findHolidaysByEndDateGreaterThanEqualOrderByStartDateAsc(LocalDate date);

    void deleteHolidayByHolidayID(String holidayID);

    Long countHolidaysByStartDateLessThanEqualAndEndDateGreaterThanEqual(LocalDate startDate, LocalDate endDate);

}
