package com.dreamland.dreamtoken.repository;

import com.dreamland.dreamtoken.entity.LedgerUserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface LedgerUserRepository extends CrudRepository<LedgerUserEntity, Integer> {
    List<LedgerUserEntity> findByUserId(Integer idUser);
    List<LedgerUserEntity> findByUserIdAndEntryTypeAndInsertDateBefore(Integer idUser, LedgerUserEntity.EntryType entryType, Date beforeDate);
}