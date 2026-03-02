package co.istad.chan.paymentservice.service;

import co.istad.chan.paymentservice.events.BookingPaymentEvent;
import co.istad.chan.paymentservice.events.SeatReservedEvent;
import co.istad.chan.paymentservice.producer.PaymentEventProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentService {


    private final PaymentEventProducer paymentEventProducer;

    public void processPayment(SeatReservedEvent event) {

        try {

            log.info("PaymentService:: Processing payment for bookingId {} with amount {}", event.bookingId(), event.amount());

            // Simulate payment failure scenario
            if (event.amount() > 2000) {
                paymentEventProducer.publishPaymentFailEvent(
                        new BookingPaymentEvent(
                                event.bookingId(),
                                false,
                                event.amount()
                        )
                );

                throw new RuntimeException("Payment amount exceeds limit for bookingId " + event.bookingId());
            } else {
                paymentEventProducer.publishPaymentSuccessEvent(
                        new BookingPaymentEvent(
                                event.bookingId(),
                                true,
                                event.amount()
                        )
                );
            }


        } catch (Exception e) {
            log.info("PaymentService:: Error processing payment for bookingId {}: {}", event.bookingId(), e.getMessage());

        }

    }
}
