package com.roomdekho.room.image;

import com.roomdekho.room.Room;
import com.roomdekho.room.RoomRepository;
import com.roomdekho.room.image.DTO.RoomImageRequestDTO;
import com.roomdekho.room.image.DTO.RoomImageResponseDTO;
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



    public RoomImageResponseDTO addImage(
            Long roomId,
            RoomImageRequestDTO dto
    ){

        Room room = roomRepository.findById(roomId)
                .orElseThrow(
                        () -> new RuntimeException("Room not found")
                );


        RoomImage image = new RoomImage();

        image.setImageUrl(dto.getImageUrl());
        image.setRoom(room);


        RoomImage saved =
                roomImageRepository.save(image);


        return new RoomImageResponseDTO(
                saved.getId(),
                saved.getImageUrl(),
                room.getId()
        );
    }



    public List<RoomImageResponseDTO> getImages(Long roomId){


        return roomImageRepository.findAll()
                .stream()
                .filter(image ->
                        image.getRoom()
                                .getId()
                                .equals(roomId)
                )
                .map(image ->
                        new RoomImageResponseDTO(
                                image.getId(),
                                image.getImageUrl(),
                                roomId
                        )
                )
                .toList();

    }



    public void deleteImage(Long id){

        roomImageRepository.deleteById(id);

    }

}