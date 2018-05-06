package com.dromree.thermopi.dbaccess.mongodb.repositories.mongodb;

import com.dromree.thermopi.dbaccess.data.holidays.Holiday;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface HolidayRepository extends MongoRepository<Holiday, String> {

    public Holiday findByHolidayID(String holidayID);

    public List<Holiday> findHolidaysByEndDateGreaterThanOrderByStartDateAsc(Date date);

    public void deleteHolidayByHolidayID(String holidayID);

    public Long countHolidaysByStartDateLessThanEqualAndEndDateGreaterThanEqual(Date startDate, Date endDate);

}
