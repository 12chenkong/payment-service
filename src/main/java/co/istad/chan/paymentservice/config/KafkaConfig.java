package co.istad.chan.paymentservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic createBookingPaymentTopic() {
        return new NewTopic("payment-event-topic", 3, (short) 1);
    }
}
