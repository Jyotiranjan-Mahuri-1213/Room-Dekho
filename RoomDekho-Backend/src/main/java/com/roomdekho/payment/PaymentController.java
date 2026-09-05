package com.roomdekho.payment;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/create-order")
    public String createOrder(
            @RequestParam Long bookingId,
            @RequestParam Double amount
    ) throws Exception {

        return paymentService.createOrder(bookingId, amount);
    }

    @PostMapping("/verify")
    public String verifyPayment(@RequestBody VerifyPaymentRequest request) {

        boolean verified = paymentService.verifyPayment(
                request.getBookingId(),
                request.getRazorpayOrderId(),
                request.getRazorpayPaymentId(),
                request.getRazorpaySignature()
        );

        if (verified) {
            return "Payment verified successfully";
        }

        return "Payment verification failed";
    }
}