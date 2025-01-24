package com.learning.journal.services.rabbitmq;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RabbitProducerTest {
    @Autowired
    private RabbitProducer rabbitProducer;

    @Test
    void sendEmailToQueueTest() {
        rabbitProducer.sendEmailToQueue("Get start 1st message with queue");
    }


}