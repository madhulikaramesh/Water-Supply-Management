
package com.watersupply.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.watersupply.api.entity.MeterReading;
import com.watersupply.api.service.MeterReadingService;

@RestController
@RequestMapping("/api/meter-readings")
@CrossOrigin(origins = "*")
public class MeterReadingController {

    private final MeterReadingService meterReadingService;

    public MeterReadingController(MeterReadingService meterReadingService) {
        this.meterReadingService = meterReadingService;
    }

    @PostMapping
    public MeterReading addMeterReading(@RequestBody MeterReading reading) {
        return meterReadingService.saveMeterReading(reading);
    }

    @GetMapping
    public List<MeterReading> getAllMeterReadings() {
        return meterReadingService.getAllMeterReadings();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MeterReading> getMeterReadingById(
            @PathVariable Integer id) {

        MeterReading reading =
                meterReadingService.getMeterReadingById(id);

        if (reading == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(reading);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMeterReading(@PathVariable Integer id) {
        meterReadingService.deleteMeterReading(id);
        return ResponseEntity.noContent().build();
    }
}
