package co.istad.chan.paymentservice.listener;

import co.istad.chan.paymentservice.events.SeatReservedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class SeatEventConsumer {


    @KafkaListener(topics = "seat-reserved-topic", groupId = "payment-service-group")
    public  void consumeSeatReservedEvent(SeatReservedEvent event) {
     try {

         if(event.reserved()){
             log.info("SeatEventConsumer:: Received seat reserved event for bookingId {}", event.bookingId());
         }else{
                log.warn("SeatEventConsumer:: Seat reservation failed for bookingId {}", event.bookingId());
         }

     }catch (Exception ex){
         log.error("SeatEventConsumer:: Error processing seat reserved event for bookingId {}: {}", event.bookingId(), ex.getMessage());
     }
    }

}
