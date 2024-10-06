package guru.qa.niffler.test.kafka;

import guru.qa.niffler.authClient.AuthApiClient;
import guru.qa.niffler.kafka.KafkaService;
import guru.qa.niffler.model.UserJson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AsyncAuthKafkaProducerTest extends BaseKafkaTest {

    private final AuthApiClient authApiClient = new AuthApiClient();

    @Test
    void messageShouldBeProducedToKafkaAfterSuccessfulRegistration() throws Exception {
        String username = "Niko001231";
        String password = "12345";
        authApiClient.register(username, password);

        UserJson userFromKafka = KafkaService.getMessage(username);

        Assertions.assertEquals(
                username,
                userFromKafka.username()
        );
    }
}