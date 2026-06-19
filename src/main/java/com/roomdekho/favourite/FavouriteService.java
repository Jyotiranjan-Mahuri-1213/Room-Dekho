package com.roomdekho.favourite;


import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FavouriteService {


    private final FavouriteRepository favouriteRepository;


    public FavouriteService(FavouriteRepository favouriteRepository){

        this.favouriteRepository = favouriteRepository;

    }



    public Favourite addFavourite(Long roomId, String email){


        Favourite favourite = new Favourite();

        favourite.setRoomId(roomId);
        favourite.setUserEmail(email);


        return favouriteRepository.save(favourite);

    }



    public List<Favourite> getMyFavourites(String email){


        return favouriteRepository.findByUserEmail(email);

    }



    public void removeFavourite(Long roomId, String email){


        favouriteRepository
                .deleteByRoomIdAndUserEmail(roomId,email);

    }

}