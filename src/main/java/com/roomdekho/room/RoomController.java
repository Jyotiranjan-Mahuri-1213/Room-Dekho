package com.roomdekho.room;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/rooms")
@CrossOrigin
public class RoomController {


    private final RoomService roomService;


    public RoomController(RoomService roomService){

        this.roomService = roomService;

    }


    @PostMapping
    public Room addRoom(@RequestBody Room room){

        return roomService.addRoom(room);

    }


    @GetMapping
    public List<Room> getRooms(){

        return roomService.getAllRooms();

    }

}