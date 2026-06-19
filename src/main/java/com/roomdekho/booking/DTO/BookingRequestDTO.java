package com.roomdekho.booking.DTO;


import java.time.LocalDateTime;


public class BookingRequestDTO {


    private Long roomId;

    private LocalDateTime bookingDate;



    public Long getRoomId() {
        return roomId;
    }


    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }


    public LocalDateTime getBookingDate() {
        return bookingDate;
    }


    public void setBookingDate(LocalDateTime bookingDate) {
        this.bookingDate = bookingDate;
    }
}