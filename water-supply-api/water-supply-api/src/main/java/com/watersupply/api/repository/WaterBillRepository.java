package com.watersupply.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.watersupply.api.entity.WaterBill;

public interface WaterBillRepository extends JpaRepository<WaterBill, Integer> {

}