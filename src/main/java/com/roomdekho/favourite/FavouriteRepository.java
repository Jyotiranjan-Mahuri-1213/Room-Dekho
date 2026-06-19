package com.roomdekho.favourite;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface FavouriteRepository
        extends JpaRepository<Favourite,Long>{


    List<Favourite> findByUserEmail(String email);


    void deleteByRoomIdAndUserEmail(
            Long roomId,
            String email
    );

}