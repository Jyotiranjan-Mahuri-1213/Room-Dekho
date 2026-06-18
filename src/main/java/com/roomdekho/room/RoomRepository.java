package com.roomdekho.room;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findByOwnerEmail(String ownerEmail);
    List<Room> findByLocationContainingIgnoreCase(String location);

    List<Room> findByRentBetween(double minRent, double maxRent);

}