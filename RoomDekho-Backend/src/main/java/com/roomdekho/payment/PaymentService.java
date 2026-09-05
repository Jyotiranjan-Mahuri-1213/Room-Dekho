package com.roomdekho.payment;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.roomdekho.booking.Booking;
import com.roomdekho.booking.BookingRepository;
import com.roomdekho.booking.BookingStatus;
import com.roomdekho.booking.PaymentStatus;


@Service
public class PaymentService {

    @Value("${razorpay.key.id}")
    private String keyId;

    @Value("${razorpay.key.secret}")
    private String keySecret;
    private final PaymentRepository paymentRepository;
    private final BookingRepository bookingRepository;

    public PaymentService(PaymentRepository paymentRepository, BookingRepository bookingRepository) {
        this.paymentRepository = paymentRepository;
        this.bookingRepository = bookingRepository;
    }

    public String createOrder(Long bookingId, Double amount) throws Exception {

        RazorpayClient razorpayClient =
                new RazorpayClient(keyId, keySecret);

        JSONObject orderRequest = new JSONObject();

        // Razorpay expects amount in paise
        int amountInPaise = (int) (amount * 100);

        orderRequest.put("amount", amountInPaise);
        orderRequest.put("currency", "INR");
        orderRequest.put("receipt", "roomdekho_receipt");

        Order order = razorpayClient.orders.create(orderRequest);

        Payment payment = new Payment();

        payment.setBookingId(bookingId);
        payment.setRazorpayOrderId(order.get("id"));
        payment.setAmount(amount);
        payment.setStatus("CREATED");
        payment.setCreatedAt(java.time.LocalDateTime.now());

        paymentRepository.save(payment);

        return order.toString();
    }

    public boolean verifyPayment(
            Long bookingId,
            String razorpayOrderId,
            String razorpayPaymentId,
            String razorpaySignature
    ) {

        try {
            String payload = razorpayOrderId + "|" + razorpayPaymentId;

            boolean isValid = com.razorpay.Utils.verifySignature(
                    payload,
                    razorpaySignature,
                    keySecret
            );

            if (!isValid) {
                return false;
            }

            Payment payment = paymentRepository
                    .findByRazorpayOrderId(razorpayOrderId)
                    .orElseThrow(() ->
                            new RuntimeException("Payment order not found"));

            payment.setBookingId(bookingId);
            payment.setRazorpayPaymentId(razorpayPaymentId);
            payment.setRazorpaySignature(razorpaySignature);
            payment.setStatus("SUCCESS");

            paymentRepository.save(payment);

            Booking booking = bookingRepository.findById(bookingId)
                    .orElseThrow(() ->
                            new RuntimeException("Booking not found"));

            booking.setPaymentStatus(PaymentStatus.SUCCESS);
            booking.setStatus(BookingStatus.APPROVED);

            bookingRepository.save(booking);

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}