package com.roomdekho.booking;

import com.roomdekho.booking.dto.PaymentDTO;
import com.roomdekho.room.Room;
import com.roomdekho.room.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final RoomRepository roomRepository;

    public BookingService(BookingRepository bookingRepository,
                          RoomRepository roomRepository) {
        this.bookingRepository = bookingRepository;
        this.roomRepository = roomRepository;
    }


    public Booking createBooking(Booking booking) {

        booking.setStatus(BookingStatus.PENDING);
        booking.setPaymentStatus(PaymentStatus.PENDING);
        booking.setCreatedAt(java.time.LocalDateTime.now());

        return bookingRepository.save(booking);
    }


    public Booking processPayment(Long id, PaymentDTO dto) {

        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        booking.setPaymentMethod(dto.getPaymentMethod());
        booking.setTransactionId(dto.getTransactionId());
        booking.setAmount(dto.getAmount());

        booking.setPaymentStatus(PaymentStatus.SUCCESS);
        booking.setStatus(BookingStatus.APPROVED);

        return bookingRepository.save(booking);
    }


    public Booking approveBooking(Long id) {

        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        if (booking.getPaymentStatus() != PaymentStatus.SUCCESS) {
            throw new RuntimeException("Payment not completed");
        }

        booking.setStatus(BookingStatus.BOOKED);

        return bookingRepository.save(booking);
    }


    public List<Booking> getMyBookings(String email) {
        return bookingRepository.findByUserEmail(email);
    }


    public List<Booking> getOwnerBookings(String ownerEmail) {

        List<Long> roomIds = roomRepository
                .findByOwnerEmail(ownerEmail)
                .stream()
                .map(Room::getId)
                .toList();

        if (roomIds.isEmpty()) {
            return List.of();
        }

        return bookingRepository.findByRoomIdIn(roomIds);
    }
}