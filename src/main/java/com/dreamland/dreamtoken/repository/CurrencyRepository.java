package com.dreamland.dreamtoken.repository;

import com.dreamland.dreamtoken.entity.CurrencyEntity;
import org.springframework.data.repository.CrudRepository;

public interface CurrencyRepository extends CrudRepository<CurrencyEntity, Integer> {
}