package com.roomdekho.booking;


import com.roomdekho.booking.DTO.BookingRequestDTO;
import com.roomdekho.booking.DTO.BookingResponseDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class BookingService {


    private final BookingRepository bookingRepository;


    public BookingService(BookingRepository bookingRepository){

        this.bookingRepository = bookingRepository;

    }



    // Create booking

    public BookingResponseDTO createBooking(
            BookingRequestDTO dto,
            String email
    ){

        Booking booking = new Booking();

        booking.setRoomId(dto.getRoomId());
        booking.setUserEmail(email);
        booking.setBookingDate(dto.getBookingDate());

        booking.setStatus(BookingStatus.PENDING);

        booking.setCreatedAt(LocalDateTime.now());


        Booking saved =
                bookingRepository.save(booking);


        return mapToResponse(saved);

    }




    // Get user's bookings

    public List<BookingResponseDTO> getMyBookings(
            String email
    ){

        return bookingRepository
                .findByUserEmail(email)
                .stream()
                .map(this::mapToResponse)
                .toList();

    }




    // Cancel booking

    public String cancelBooking(
            Long id,
            String email
    ){

        Booking booking =
                bookingRepository.findById(id)
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Booking not found"
                                )
                        );


        if(!booking.getUserEmail().equals(email)){

            throw new RuntimeException(
                    "You cannot cancel this booking"
            );

        }


        booking.setStatus(
                BookingStatus.CANCELLED
        );


        bookingRepository.save(booking);


        return "Booking cancelled successfully";

    }





    private BookingResponseDTO mapToResponse(
            Booking booking
    ){

        BookingResponseDTO dto =
                new BookingResponseDTO();


        dto.setId(booking.getId());

        dto.setRoomId(
                booking.getRoomId()
        );

        dto.setUserEmail(
                booking.getUserEmail()
        );

        dto.setBookingDate(
                booking.getBookingDate()
        );

        dto.setStatus(
                booking.getStatus()
        );


        return dto;

    }

    public List<BookingResponseDTO> getOwnerBookings(String email){

        return bookingRepository
                .findOwnerBookings(email)
                .stream()
                .map(this::mapToResponse)
                .toList();

    }

    public BookingResponseDTO approveBooking(Long id, String email){

        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        booking.setStatus(BookingStatus.APPROVED);

        Booking saved = bookingRepository.save(booking);

        return mapToResponse(saved);
    }



    public BookingResponseDTO rejectBooking(Long id, String email){

        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        booking.setStatus(BookingStatus.REJECTED);

        Booking saved = bookingRepository.save(booking);

        return mapToResponse(saved);
    }

}