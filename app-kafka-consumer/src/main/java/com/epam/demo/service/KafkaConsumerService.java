package com.epam.demo.service;

import com.epam.demo.model.Commit;

public interface KafkaConsumerService {

    void receive(Commit message);
}
