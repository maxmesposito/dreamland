package com.dreamland.dreamtoken.repository;

import com.dreamland.dreamtoken.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {

}