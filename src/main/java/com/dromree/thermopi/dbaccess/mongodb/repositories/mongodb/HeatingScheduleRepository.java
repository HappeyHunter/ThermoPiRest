package com.dromree.thermopi.dbaccess.mongodb.repositories.mongodb;

import com.dromree.thermopi.dbaccess.data.heatingschedule.WeekSchedule;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HeatingScheduleRepository extends MongoRepository<WeekSchedule, String> {

    public WeekSchedule findWeekScheduleByMonth(Integer month);

}
