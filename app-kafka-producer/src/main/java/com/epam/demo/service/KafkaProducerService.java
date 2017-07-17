package com.epam.demo.service;

import com.epam.demo.model.Commit;

public interface KafkaProducerService {

    void sendMessage(String topic, Commit msg);
}
