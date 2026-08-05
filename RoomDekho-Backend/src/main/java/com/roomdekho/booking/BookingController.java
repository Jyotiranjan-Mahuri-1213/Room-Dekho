package com.roomdekho.booking;

import com.roomdekho.booking.dto.PaymentDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }


    @PostMapping("/create")
    public Booking create(@RequestBody Booking booking) {
        return bookingService.createBooking(booking);
    }


    @PostMapping("/payment/{id}")
    public Booking payment(@PathVariable Long id,
                           @RequestBody PaymentDTO dto) {
        return bookingService.processPayment(id, dto);
    }


    @PutMapping("/approve/{id}")
    public Booking approve(@PathVariable Long id) {
        return bookingService.approveBooking(id);
    }


    @GetMapping("/my")
    public List<Booking> myBookings(@RequestParam String email) {
        return bookingService.getMyBookings(email);
    }


    @GetMapping("/owner")
    public List<Booking> ownerBookings(@RequestParam String email) {
        return bookingService.getOwnerBookings(email);
    }
}