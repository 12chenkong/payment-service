package co.istad.chan.paymentservice.producer;

import co.istad.chan.paymentservice.events.SeatReservedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PaymentProducer {

    private final KafkaTemplate<String,Object>template;

    public void publishPaymentEventSuccess(SeatReservedEvent event){
                   log.info("Pushlishing payment success event for bookingId {}", event.bookingId());
    }
}
