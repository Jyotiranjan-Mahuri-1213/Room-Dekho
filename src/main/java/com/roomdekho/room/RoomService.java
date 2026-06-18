package com.roomdekho.room;

import com.roomdekho.room.DTO.RoomRequestDTO;
import com.roomdekho.room.DTO.RoomResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository){
        this.roomRepository = roomRepository;
    }

    public RoomResponseDTO createRoom(RoomRequestDTO dto, String email) {

        Room room = new Room();
        room.setTitle(dto.getTitle());
        room.setLocation(dto.getLocation());
        room.setRent(dto.getRent());
        room.setDescription(dto.getDescription());
        room.setOwnerEmail(email);

        Room saved = roomRepository.save(room);

        return mapToResponse(saved);
    }

    public List<RoomResponseDTO> getAllRooms(){

        return roomRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private RoomResponseDTO mapToResponse(Room room){

        RoomResponseDTO dto = new RoomResponseDTO();
        dto.setId(room.getId());
        dto.setTitle(room.getTitle());
        dto.setLocation(room.getLocation());
        dto.setRent(room.getRent());
        dto.setOwnerEmail(room.getOwnerEmail());

        return dto;
    }
    public void deleteRoom(Long id, String email){

        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found"));

        if(!room.getOwnerEmail().equals(email)){
            throw new RuntimeException("You are not allowed to delete this room");
        }

        roomRepository.delete(room);
    }

    public RoomResponseDTO updateRoom(Long id, RoomRequestDTO dto, String email){

        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found"));

        if(!room.getOwnerEmail().equals(email)){
            throw new RuntimeException("You are not allowed to update this room");
        }

        room.setTitle(dto.getTitle());
        room.setLocation(dto.getLocation());
        room.setRent(dto.getRent());
        room.setDescription(dto.getDescription());

        Room updated = roomRepository.save(room);

        return mapToResponse(updated);
    }

    public List<RoomResponseDTO> searchByLocation(String location){

        return roomRepository.findByLocationContainingIgnoreCase(location)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<RoomResponseDTO> filterByRent(double min, double max){

        return roomRepository.findByRentBetween(min, max)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<RoomResponseDTO> getAllRooms(int page, int size, String sortBy){

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        Page<Room> roomsPage = roomRepository.findAll(pageable);

        return roomsPage
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<RoomResponseDTO> getMyRooms(String email){

        return roomRepository.findByOwnerEmail(email)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }
}