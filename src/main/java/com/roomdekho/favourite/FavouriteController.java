package com.roomdekho.favourite;


import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;


@RestController
@RequestMapping("/api/favourites")
@CrossOrigin
public class FavouriteController {


    private final FavouriteService favouriteService;


    public FavouriteController(FavouriteService favouriteService){

        this.favouriteService = favouriteService;

    }



    @PostMapping("/{roomId}")
    public Favourite addFavourite(
            @PathVariable Long roomId
    ){

        String email =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal()
                        .toString();


        return favouriteService.addFavourite(roomId,email);

    }



    @GetMapping
    public List<Favourite> getMyFavourites(){


        String email =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal()
                        .toString();


        return favouriteService.getMyFavourites(email);

    }



    @DeleteMapping("/{roomId}")
    public String removeFavourite(
            @PathVariable Long roomId
    ){

        String email =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal()
                        .toString();


        favouriteService.removeFavourite(roomId,email);


        return "Removed from favourites";

    }

}