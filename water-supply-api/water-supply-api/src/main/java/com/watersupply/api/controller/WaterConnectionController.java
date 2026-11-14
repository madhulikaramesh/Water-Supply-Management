
package com.watersupply.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.watersupply.api.entity.WaterConnection;
import com.watersupply.api.service.WaterConnectionService;

@RestController
@RequestMapping("/api/water-connections")
@CrossOrigin(origins = "*")
public class WaterConnectionController {

    private final WaterConnectionService waterConnectionService;

    public WaterConnectionController(WaterConnectionService waterConnectionService) {
        this.waterConnectionService = waterConnectionService;
    }

    @PostMapping
    public WaterConnection addWaterConnection(@RequestBody WaterConnection connection) {
        return waterConnectionService.saveWaterConnection(connection);
    }

    @GetMapping
    public List<WaterConnection> getAllWaterConnections() {
        return waterConnectionService.getAllWaterConnections();
    }

    @GetMapping("/{id}")
    public ResponseEntity<WaterConnection> getWaterConnectionById(
            @PathVariable Integer id) {

        WaterConnection connection =
                waterConnectionService.getWaterConnectionById(id);

        if (connection == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(connection);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWaterConnection(@PathVariable Integer id) {
        waterConnectionService.deleteWaterConnection(id);
        return ResponseEntity.noContent().build();
    }
}
