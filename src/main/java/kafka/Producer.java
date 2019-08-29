package kafka;

import org.apache.kafka.clients.producer.internals.Sender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {

    private static final Logger LOG = LoggerFactory.getLogger(Sender.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${app.topic.see}")
    private String topic;

    @Value("${app.topic.fee}")
    private String topic2;

    public void send(String message) {

        System.out.println("sent this: " + message + " to topic: " + topic);
        System.out.println("sent this: " + message + " to topic: " + topic2);
        LOG.info("sending message='{}' to topic='{}'", message, topic);
        kafkaTemplate.send(topic, message);
        kafkaTemplate.send(topic2, message);

    }
}