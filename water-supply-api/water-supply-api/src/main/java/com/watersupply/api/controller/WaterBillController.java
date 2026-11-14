
package com.watersupply.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.watersupply.api.entity.WaterBill;
import com.watersupply.api.service.WaterBillService;

@RestController
@RequestMapping("/api/water-bills")
@CrossOrigin(origins = "*")
public class WaterBillController {

    private final WaterBillService waterBillService;

    public WaterBillController(WaterBillService waterBillService) {
        this.waterBillService = waterBillService;
    }

    @PostMapping
    public WaterBill addWaterBill(@RequestBody WaterBill bill) {
        return waterBillService.saveWaterBill(bill);
    }

    @GetMapping
    public List<WaterBill> getAllWaterBills() {
        return waterBillService.getAllWaterBills();
    }

    @GetMapping("/{id}")
    public ResponseEntity<WaterBill> getWaterBillById(@PathVariable Integer id) {
        WaterBill bill = waterBillService.getWaterBillById(id);

        if (bill == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(bill);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWaterBill(@PathVariable Integer id) {
        waterBillService.deleteWaterBill(id);
        return ResponseEntity.noContent().build();
    }
}
