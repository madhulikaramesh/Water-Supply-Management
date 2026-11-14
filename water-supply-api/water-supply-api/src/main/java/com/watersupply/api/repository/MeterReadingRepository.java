package com.watersupply.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.watersupply.api.entity.MeterReading;

public interface MeterReadingRepository extends JpaRepository<MeterReading, Integer> {

}