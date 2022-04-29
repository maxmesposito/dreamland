package com.dreamland.dreamtoken.repository;

import com.dreamland.dreamtoken.entity.DreamtokenRateEntity;

import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.Optional;


public interface DreamtokenRateRepository extends CrudRepository<DreamtokenRateEntity, Integer> {
   Optional<DreamtokenRateEntity> findByCurrencyIdAndStartDateBeforeAndEndDateAfter(Integer id, Date startDateAfter, Date endDateBefore);

}