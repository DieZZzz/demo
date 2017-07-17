package com.epam.demo.service;

import com.epam.demo.model.Commit;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

@Service
public class KafkaConsumerServiceImpl implements KafkaConsumerService {

    private CountDownLatch latch = new CountDownLatch(1);

    public CountDownLatch getLatch() {
        return latch;
    }

    @Override
    @KafkaListener(topics = "${kafka.topic}")
    public void receive(Commit message) {
        latch.countDown();
    }
}
