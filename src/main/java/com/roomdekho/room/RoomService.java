package com.roomdekho.room;

import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class RoomService {

    private final RoomRepository roomRepository;


    public RoomService(RoomRepository roomRepository){
        this.roomRepository = roomRepository;
    }


    public Room addRoom(Room room){

        return roomRepository.save(room);

    }


    public List<Room> getAllRooms(){

        return roomRepository.findAll();

    }
}