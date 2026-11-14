package com.watersupply.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.watersupply.api.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

}