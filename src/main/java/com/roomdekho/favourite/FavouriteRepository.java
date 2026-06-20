package com.roomdekho.favourite;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface FavouriteRepository
        extends JpaRepository<Favourite,Long>{


    List<Favourite> findByUserEmail(String email);



    @Transactional
    @Modifying
    void deleteByRoomIdAndUserEmail(
            Long roomId,
            String email
    );

}