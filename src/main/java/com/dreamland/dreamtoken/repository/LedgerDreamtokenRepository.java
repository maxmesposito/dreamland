package com.dreamland.dreamtoken.repository;

import com.dreamland.dreamtoken.entity.LedgerDreamtokenEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface LedgerDreamtokenRepository extends CrudRepository<LedgerDreamtokenEntity, Integer> {

    List<LedgerDreamtokenEntity> findByUserIdAndIssueDateBetween(Integer idUser, Date startDate, Date endDate);
}