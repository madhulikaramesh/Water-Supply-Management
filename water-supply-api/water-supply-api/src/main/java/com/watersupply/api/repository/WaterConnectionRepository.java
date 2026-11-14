package com.watersupply.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.watersupply.api.entity.WaterConnection;

public interface WaterConnectionRepository extends JpaRepository<WaterConnection, Integer> {

}