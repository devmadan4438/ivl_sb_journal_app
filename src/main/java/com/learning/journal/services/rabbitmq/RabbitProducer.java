package com.learning.journal.services.rabbitmq;

import com.learning.journal.constants.QueueNames;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitProducer {

    private final RabbitTemplate rabbitTemplate;

    RabbitProducer(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendEmailToQueue(String message) {
        rabbitTemplate.convertAndSend(QueueNames.PROCESS_EMAIL_EXC, QueueNames.PROCESS_EMAIL_RK_1, message);
    }
}
