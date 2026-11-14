
package com.watersupply.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.watersupply.api.entity.Consumer;
import com.watersupply.api.service.ConsumerService;

@RestController
@RequestMapping("/api/consumers")
@CrossOrigin(origins = "*")
public class ConsumerController {

    private final ConsumerService consumerService;

    public ConsumerController(ConsumerService consumerService) {
        this.consumerService = consumerService;
    }

    @PostMapping
    public Consumer addConsumer(@RequestBody Consumer consumer) {
        return consumerService.saveConsumer(consumer);
    }

    @GetMapping
    public List<Consumer> getAllConsumers() {
        return consumerService.getAllConsumers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Consumer> getConsumerById(@PathVariable Integer id) {
        Consumer consumer = consumerService.getConsumerById(id);

        if (consumer == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(consumer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConsumer(@PathVariable Integer id) {
        consumerService.deleteConsumer(id);
        return ResponseEntity.noContent().build();
    }
}
