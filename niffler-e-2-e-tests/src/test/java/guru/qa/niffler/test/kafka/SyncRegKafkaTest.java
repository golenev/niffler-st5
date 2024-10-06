package guru.qa.niffler.test.kafka;

import com.fasterxml.jackson.databind.JsonSerializer;
import guru.qa.niffler.authClient.AuthApiClient;
import guru.qa.niffler.config.Config;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import static guru.qa.niffler.kafka.KafkaService.CFG;

public class SyncRegKafkaTest {

    public static final Config CFG1 = Config.getInstance();
    private final AuthApiClient authApiClient = new AuthApiClient();

    @Test
    void messageShouldBeProducedToKafkaAfterSuccessfulRegistration() throws IOException {
        //тут мы создаём нового консюмера/получателя, который считает из кафки то, что мы отправили через РЕСТ при регистрации
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, CFG1.kafkaAddress());
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "stringKafkaStringConsumerService");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        Consumer consumer = new KafkaConsumer(properties);
        consumer.subscribe(CFG1.kafkaTopics());
        String username = "FriedrichQwe";
        String password = "12345";
        authApiClient.register(username, password);
        ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(10000));
        consumer.close();
    }

}
