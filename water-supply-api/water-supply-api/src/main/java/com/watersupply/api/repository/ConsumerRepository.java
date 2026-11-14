package com.watersupply.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.watersupply.api.entity.Consumer;

public interface ConsumerRepository extends JpaRepository<Consumer, Integer> {

}