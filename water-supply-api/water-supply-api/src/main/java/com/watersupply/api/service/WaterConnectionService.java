
package com.watersupply.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.watersupply.api.entity.WaterConnection;
import com.watersupply.api.repository.WaterConnectionRepository;

@Service
public class WaterConnectionService {

    private final WaterConnectionRepository waterConnectionRepository;

    public WaterConnectionService(WaterConnectionRepository waterConnectionRepository) {
        this.waterConnectionRepository = waterConnectionRepository;
    }

    public WaterConnection saveWaterConnection(WaterConnection connection) {
        return waterConnectionRepository.save(connection);
    }

    public List<WaterConnection> getAllWaterConnections() {
        return waterConnectionRepository.findAll();
    }

    public WaterConnection getWaterConnectionById(Integer id) {
        return waterConnectionRepository.findById(id).orElse(null);
    }

    public void deleteWaterConnection(Integer id) {
        waterConnectionRepository.deleteById(id);
    }
}
