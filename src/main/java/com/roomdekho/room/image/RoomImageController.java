package com.roomdekho.room.image;


import com.roomdekho.room.image.DTO.RoomImageRequestDTO;
import com.roomdekho.room.image.DTO.RoomImageResponseDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/rooms")
@CrossOrigin
public class RoomImageController {


    private final RoomImageService roomImageService;


    public RoomImageController(RoomImageService roomImageService){
        this.roomImageService = roomImageService;
    }



    @PostMapping("/{roomId}/images")
    public RoomImageResponseDTO addImage(
            @PathVariable Long roomId,
            @RequestBody RoomImageRequestDTO dto
    ){

        return roomImageService.addImage(roomId,dto);

    }



    @GetMapping("/{roomId}/images")
    public List<RoomImageResponseDTO> getImages(
            @PathVariable Long roomId
    ){

        return roomImageService.getImages(roomId);

    }



    @DeleteMapping("/images/{id}")
    public String deleteImage(
            @PathVariable Long id
    ){

        roomImageService.deleteImage(id);

        return "Image deleted successfully";
    }

}