package com.dromree.thermopi.dbaccess.mongodb.repositories.mongodb;

import com.dromree.thermopi.dbaccess.data.heatingschedule.WeekSchedule;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeatingScheduleRepository extends MongoRepository<WeekSchedule, String> {

    @Cacheable("schedule")
    public WeekSchedule findWeekScheduleByMonth(Integer month);

    @Override
    @CachePut("schedule")
    <S extends WeekSchedule> S save(S schedule);

}
