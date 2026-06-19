package com.roomdekho.booking.DTO;


import com.roomdekho.booking.BookingStatus;

import java.time.LocalDateTime;


public class BookingResponseDTO {


    private Long id;

    private Long roomId;

    private String userEmail;

    private LocalDateTime bookingDate;

    private BookingStatus status;



    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public Long getRoomId() {
        return roomId;
    }


    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }


    public String getUserEmail() {
        return userEmail;
    }


    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }


    public LocalDateTime getBookingDate() {
        return bookingDate;
    }


    public void setBookingDate(LocalDateTime bookingDate) {
        this.bookingDate = bookingDate;
    }


    public BookingStatus getStatus() {
        return status;
    }


    public void setStatus(BookingStatus status) {
        this.status = status;
    }
}