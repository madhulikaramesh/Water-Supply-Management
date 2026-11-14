
package com.watersupply.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.watersupply.api.entity.WaterBill;
import com.watersupply.api.repository.WaterBillRepository;

@Service
public class WaterBillService {

    private final WaterBillRepository waterBillRepository;

    public WaterBillService(WaterBillRepository waterBillRepository) {
        this.waterBillRepository = waterBillRepository;
    }

    public WaterBill saveWaterBill(WaterBill bill) {
        return waterBillRepository.save(bill);
    }

    public List<WaterBill> getAllWaterBills() {
        return waterBillRepository.findAll();
    }

    public WaterBill getWaterBillById(Integer id) {
        return waterBillRepository.findById(id).orElse(null);
    }

    public void deleteWaterBill(Integer id) {
        waterBillRepository.deleteById(id);
    }
}
