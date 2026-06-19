package com.roomdekho.booking;


import com.roomdekho.booking.DTO.BookingRequestDTO;
import com.roomdekho.booking.DTO.BookingResponseDTO;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/bookings")
@CrossOrigin
public class BookingController {


    private final BookingService bookingService;


    public BookingController(BookingService bookingService){

        this.bookingService = bookingService;

    }



    // Create booking

    @PostMapping
    public BookingResponseDTO createBooking(
            @RequestBody BookingRequestDTO dto
    ){

        String email =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal()
                        .toString();


        return bookingService.createBooking(dto,email);

    }

    @GetMapping("/owner")
    public List<BookingResponseDTO> getOwnerBookings(){

        String email =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal()
                        .toString();


        return bookingService.getOwnerBookings(email);

    }


    // Get logged in user's bookings

    @GetMapping("/my")
    public List<BookingResponseDTO> getMyBookings(){

        String email =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal()
                        .toString();


        return bookingService.getMyBookings(email);

    }





    // Cancel booking

    @DeleteMapping("/{id}")
    public String cancelBooking(
            @PathVariable Long id
    ){

        String email =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal()
                        .toString();


        return bookingService.cancelBooking(id,email);

    }

    @PutMapping("/{id}/approve")
    public BookingResponseDTO approveBooking(
            @PathVariable Long id
    ){

        String email =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal()
                        .toString();


        return bookingService.approveBooking(id,email);

    }



    @PutMapping("/{id}/reject")
    public BookingResponseDTO rejectBooking(
            @PathVariable Long id
    ){

        String email =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal()
                        .toString();


        return bookingService.rejectBooking(id,email);

    }

}