package com.smartplug.test.repository;

import com.smartplug.test.entity.SmartPlug;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SmartPlugRepository extends ReactiveCrudRepository<SmartPlug, Integer> {
}
