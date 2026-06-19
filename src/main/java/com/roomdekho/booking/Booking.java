package com.roomdekho.booking;

import jakarta.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name="bookings")
public class Booking {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private Long roomId;


    private String userEmail;


    private LocalDateTime bookingDate;


    @Enumerated(EnumType.STRING)
    private BookingStatus status;


    private LocalDateTime createdAt;



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


    public LocalDateTime getCreatedAt() {
        return createdAt;
    }


    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}