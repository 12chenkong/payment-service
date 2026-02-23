package co.istad.chan.paymentservice.events;

public record SeatReservedEvent(
        String bookingId, boolean reserved, long amount
) {}