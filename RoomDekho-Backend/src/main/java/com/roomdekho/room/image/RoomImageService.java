package com.roomdekho.room.image;


import com.roomdekho.room.Room;
import com.roomdekho.room.RoomRepository;
import com.roomdekho.room.image.dto.RoomImageRequestDTO;
import com.roomdekho.room.image.dto.RoomImageResponseDTO;

import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class RoomImageService {


    private final RoomImageRepository roomImageRepository;

    private final RoomRepository roomRepository;



    public RoomImageService(
            RoomImageRepository roomImageRepository,
            RoomRepository roomRepository
    ){

        this.roomImageRepository = roomImageRepository;

        this.roomRepository = roomRepository;

    }





    // ADD IMAGE TO ROOM

    public RoomImageResponseDTO addImage(
            Long roomId,
            RoomImageRequestDTO request
    ){


        Room room =
                roomRepository.findById(roomId)
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Room not found"
                                )
                        );



        RoomImage image =
                new RoomImage();



        image.setImageUrl(
                request.getImageUrl()
        );



        image.setRoom(room);



        RoomImage saved =
                roomImageRepository.save(image);



        return mapToDTO(saved);

    }





    // GET ALL IMAGES OF ROOM

    public List<RoomImageResponseDTO> getImagesByRoom(
            Long roomId
    ){


        return roomImageRepository
                .findByRoomId(roomId)
                .stream()
                .map(this::mapToDTO)
                .toList();

    }





    // DELETE IMAGE

    public String deleteImage(Long id){


        RoomImage image =
                roomImageRepository.findById(id)
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Image not found"
                                )
                        );


        roomImageRepository.delete(image);


        return "Image deleted successfully";

    }





    // ENTITY -> DTO

    private RoomImageResponseDTO mapToDTO(
            RoomImage image
    ){


        RoomImageResponseDTO dto =
                new RoomImageResponseDTO();



        dto.setId(
                image.getId()
        );



        dto.setImageUrl(
                image.getImageUrl()
        );



        dto.setRoomId(
                image.getRoom().getId()
        );



        return dto;

    }

}