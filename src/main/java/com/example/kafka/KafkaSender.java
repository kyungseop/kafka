package com.example.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class KafkaSender {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaSender(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    public void send(String kafkaTopic, String message) {
        log.info("KafkaSender => kafkaTopic : {}, message : {}", kafkaTopic, message);
        kafkaTemplate.send(kafkaTopic, message);
    }

}
