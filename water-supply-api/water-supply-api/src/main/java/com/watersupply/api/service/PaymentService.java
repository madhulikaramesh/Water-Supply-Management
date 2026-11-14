
package com.watersupply.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.watersupply.api.entity.Payment;
import com.watersupply.api.repository.PaymentRepository;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment savePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Payment getPaymentById(Integer id) {
        return paymentRepository.findById(id).orElse(null);
    }

    public void deletePayment(Integer id) {
        paymentRepository.deleteById(id);
    }
}
