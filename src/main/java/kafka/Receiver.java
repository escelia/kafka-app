package kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;


@Service
public class Receiver {

    private static final Logger LOG = LoggerFactory.getLogger(Receiver.class);

    @KafkaListener(topics = {"${app.topic.see}", "${app.topic.fee}"})
    public void listen(@Payload String message) {
        System.out.println("Received this: " + message);
        LOG.info("received message='{}'", message);
    }

}