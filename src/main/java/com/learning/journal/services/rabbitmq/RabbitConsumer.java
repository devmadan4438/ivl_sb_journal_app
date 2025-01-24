package com.learning.journal.services.rabbitmq;

import com.learning.journal.constants.QueueNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RabbitConsumer {

    @RabbitListener(queues = QueueNames.QUEUE_NAME)
    public void processEmailFromQueue(String message){
        log.info("{} Message from consumer", message);
    }
}
