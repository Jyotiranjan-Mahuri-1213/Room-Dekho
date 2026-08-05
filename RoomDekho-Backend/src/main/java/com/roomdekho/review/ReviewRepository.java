package com.roomdekho.review;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository
        extends JpaRepository<Review,Long>{


    List<Review> findByRoomId(Long roomId);


    List<Review> findByUserEmail(String email);

    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.roomId = :roomId")
    Double getAverageRating(@Param("roomId") Long roomId);
}