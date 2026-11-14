
package com.watersupply.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.watersupply.api.entity.MeterReading;
import com.watersupply.api.repository.MeterReadingRepository;

@Service
public class MeterReadingService {

    private final MeterReadingRepository meterReadingRepository;

    public MeterReadingService(MeterReadingRepository meterReadingRepository) {
        this.meterReadingRepository = meterReadingRepository;
    }

    public MeterReading saveMeterReading(MeterReading reading) {
        return meterReadingRepository.save(reading);
    }

    public List<MeterReading> getAllMeterReadings() {
        return meterReadingRepository.findAll();
    }

    public MeterReading getMeterReadingById(Integer id) {
        return meterReadingRepository.findById(id).orElse(null);
    }

    public void deleteMeterReading(Integer id) {
        meterReadingRepository.deleteById(id);
    }
}
