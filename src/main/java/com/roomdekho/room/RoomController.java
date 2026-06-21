package com.roomdekho.room;

import com.roomdekho.room.DTO.RoomRequestDTO;
import com.roomdekho.room.DTO.RoomResponseDTO;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.context.SecurityContextHolder;

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
    public RoomResponseDTO createRoom(@RequestBody RoomRequestDTO dto) {
        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal()
                .toString();

        return roomService.createRoom(dto, email);
    }

    @GetMapping
    public List<RoomResponseDTO> getRooms(){
        return roomService.getAllRooms();
    }

    @GetMapping("/my")
    public List<RoomResponseDTO> getMyRooms(){
        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal()
                .toString();

        return roomService.getMyRooms(email);
    }

    @GetMapping("/{id}")
    public RoomResponseDTO getRoomById(
            @PathVariable Long id
    ){
        return roomService.getRoomById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteRoom(@PathVariable Long id){
        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal()
                .toString();

        roomService.deleteRoom(id, email);
        return "Room deleted successfully";
    }

    @PutMapping("/{id}")
    public RoomResponseDTO updateRoom(
            @PathVariable Long id,
            @RequestBody RoomRequestDTO dto
    ){
        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal()
                .toString();

        return roomService.updateRoom(id, dto, email);
    }

    @GetMapping("/search")
    public List<RoomResponseDTO> searchRooms(@RequestParam String location){
        return roomService.searchByLocation(location);
    }

    @GetMapping("/filter")
    public List<RoomResponseDTO> filterRooms(
            @RequestParam double min,
            @RequestParam double max
    ){
        return roomService.filterByRent(min, max);
    }

    @GetMapping("/paged")
    public List<RoomResponseDTO> getRoomsPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy
    ){
        return roomService.getAllRooms(page, size, sortBy);
    }
}