package co.istad.chan.paymentservice.producer;

import co.istad.chan.paymentservice.events.BookingPaymentEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PaymentEventProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;


// this is called when fail event accur
    public void publishPaymentFailEvent(BookingPaymentEvent paymentEvent){

        log.info("PaymentEventProducer:: Publishing payment failed event for bookingId {}", paymentEvent.bookingId());

        BookingPaymentEvent failedEvent = new BookingPaymentEvent(
                paymentEvent.bookingId(),
                false,
                paymentEvent.amount()
        );

        kafkaTemplate.send("payment-event-topic",paymentEvent.bookingId(),failedEvent);

    }

    public void publishPaymentSuccessEvent(BookingPaymentEvent paymentEvent){

        log.info("PaymentEventProducer:: Publishing payment success event for bookingId {}", paymentEvent.bookingId());

        BookingPaymentEvent successEvent = new BookingPaymentEvent(
                paymentEvent.bookingId(),
                true,
                paymentEvent.amount()
        );
        kafkaTemplate.send("payment-event-topic",paymentEvent.bookingId(),successEvent);
     }


}
