package co.istad.chan.paymentservice.events;

public record BookingPaymentEvent(String bookingId, boolean paymentCompleted, long amount) {
}