package com.roomdekho.room.image;


import com.roomdekho.room.image.dto.RoomImageRequestDTO;
import com.roomdekho.room.image.dto.RoomImageResponseDTO;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/rooms")
@CrossOrigin
public class RoomImageController {


    private final RoomImageService roomImageService;


    public RoomImageController(
            RoomImageService roomImageService
    ){

        this.roomImageService = roomImageService;

    }



    // ADD IMAGE TO ROOM
    @PostMapping("/{roomId}/images")
    public RoomImageResponseDTO addImage(
            @PathVariable Long roomId,
            @RequestBody RoomImageRequestDTO request
    ){

        return roomImageService.addImage(
                roomId,
                request
        );

    }




    // GET ALL IMAGES OF ROOM
    @GetMapping("/{roomId}/images")
    public List<RoomImageResponseDTO> getImages(
            @PathVariable Long roomId
    ){

        return roomImageService.getImagesByRoom(
                roomId
        );

    }




    // DELETE IMAGE
    @DeleteMapping("/images/{id}")
    public String deleteImage(
            @PathVariable Long id
    ){

        return roomImageService.deleteImage(id);

    }

}