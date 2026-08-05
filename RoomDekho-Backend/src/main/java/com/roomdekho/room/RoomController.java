package com.roomdekho.room;

import com.roomdekho.room.dto.RoomRequestDTO;
import com.roomdekho.room.dto.RoomResponseDTO;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@CrossOrigin
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    // ✅ CREATE ROOM (OWNER ONLY)
    @PostMapping
    public RoomResponseDTO createRoom(@RequestBody RoomRequestDTO dto) {

        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        return roomService.createRoom(dto, email);
    }

    // ✅ GET ALL ROOMS (PUBLIC)
    @GetMapping
    public List<RoomResponseDTO> getAllRooms() {
        return roomService.getAllRooms();
    }

    // ✅ GET ROOM BY ID (PUBLIC)
    @GetMapping("/{id}")
    public RoomResponseDTO getRoomById(@PathVariable Long id) {
        return roomService.getRoomById(id);
    }

    // ✅ GET MY ROOMS (OWNER)
    @GetMapping("/my")
    public List<RoomResponseDTO> getMyRooms() {

        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        return roomService.getMyRooms(email);
    }

    // ✅ DELETE ROOM (OWNER ONLY)
    @DeleteMapping("/{id}")
    public String deleteRoom(@PathVariable Long id) {

        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        roomService.deleteRoom(id, email);

        return "Room deleted successfully";
    }

    // ✅ UPDATE ROOM (OWNER ONLY)
    @PutMapping("/{id}")
    public RoomResponseDTO updateRoom(
            @PathVariable Long id,
            @RequestBody RoomRequestDTO dto
    ) {

        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        return roomService.updateRoom(id, dto, email);
    }

    // ✅ SEARCH BY LOCATION and Room type
    @GetMapping("/search/filter")
    public List<RoomResponseDTO> searchFilter(
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String roomType,
            @RequestParam(required = false) Double minRent,
            @RequestParam(required = false) Double maxRent
    ) {
        return roomService.searchFilter(location, roomType, minRent, maxRent);
    }



}