
package com.watersupply.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.watersupply.api.entity.Consumer;
import com.watersupply.api.repository.ConsumerRepository;

@Service
public class ConsumerService {

    private final ConsumerRepository consumerRepository;

    public ConsumerService(ConsumerRepository consumerRepository) {
        this.consumerRepository = consumerRepository;
    }

    public Consumer saveConsumer(Consumer consumer) {
        return consumerRepository.save(consumer);
    }

    public List<Consumer> getAllConsumers() {
        return consumerRepository.findAll();
    }

    public Consumer getConsumerById(Integer id) {
        return consumerRepository.findById(id).orElse(null);
    }

    public void deleteConsumer(Integer id) {
        consumerRepository.deleteById(id);
    }
}
