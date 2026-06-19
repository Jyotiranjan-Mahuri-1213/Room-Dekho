package com.roomdekho.booking;


import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;


public interface BookingRepository
        extends JpaRepository<Booking,Long>{


    List<Booking> findByUserEmail(String email);


    @Query("""
            SELECT b FROM Booking b
            WHERE b.roomId IN
            (
              SELECT r.id FROM Room r
              WHERE r.ownerEmail = :email
            )
            """)
    List<Booking> findOwnerBookings(String email);


}