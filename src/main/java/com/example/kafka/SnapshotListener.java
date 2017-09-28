package com.example.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class SnapshotListener {

    @Autowired
    KafkaSender kafkaSender;

    @KafkaListener(topics = "syslog", group = "test-consumer-group")
    public void listenChangeLog(String message) {
        log.info("Received Message in listenChangeLog: " + message);
        kafkaSender.send("collect", "collect firewall policy.!!");
        log.info("Send Message to policy collector");
    }

    @KafkaListener(topics = "collect-finish", group = "test-consumer-group")
    public void listenCollectFinish(String changelogId) {
        log.info("Received Message from  policy collector: " + changelogId);
        log.info("Will save revision ({}) and changelog({}) ", UUID.randomUUID(), changelogId);
    }
}
