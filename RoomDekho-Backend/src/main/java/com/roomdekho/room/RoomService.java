package com.roomdekho.room;

import com.roomdekho.room.dto.RoomRequestDTO;
import com.roomdekho.room.dto.RoomResponseDTO;
import com.roomdekho.room.image.RoomImage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.roomdekho.review.ReviewRepository;

@Service
public class RoomService {

    private final RoomRepository roomRepository;
    private final ReviewRepository reviewRepository;
    public RoomService(RoomRepository roomRepository,
                       ReviewRepository reviewRepository) {
        this.roomRepository = roomRepository;
        this.reviewRepository = reviewRepository;
    }

    // CREATE ROOM
    public RoomResponseDTO createRoom(RoomRequestDTO dto, String email) {

        Room room = new Room();
        room.setTitle(dto.getTitle());
        room.setLocation(dto.getLocation());
        room.setRent(dto.getRent());
        room.setDescription(dto.getDescription());
        room.setOwnerEmail(email);
        room.setRoomType(dto.getRoomType());
        List<RoomImage> imageList = new ArrayList<>();

        if (dto.getImages() != null) {
            for (String img : dto.getImages()) {

                RoomImage image = new RoomImage();
                image.setImageUrl(img);
                image.setRoom(room); // ✅ FIX IMPORTANT

                imageList.add(image);
            }
        }

        room.setImages(imageList);

        Room saved = roomRepository.save(room);
        return mapToDTO(saved);
    }

    // GET ALL ROOMS
    public List<RoomResponseDTO> getAllRooms() {
        return roomRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // GET BY ID
    public RoomResponseDTO getRoomById(Long id) {

        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found"));

        return mapToDTO(room);
    }

    // MY ROOMS
    public List<RoomResponseDTO> getMyRooms(String email) {
        return roomRepository.findByOwnerEmail(email)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // DELETE
    public void deleteRoom(Long id, String email) {

        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found"));

        if (!room.getOwnerEmail().equals(email)) {
            throw new RuntimeException("Not allowed");
        }

        roomRepository.delete(room);
    }

    // UPDATE ROOM (FIXED)
    public RoomResponseDTO updateRoom(Long id, RoomRequestDTO dto, String email) {

        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found"));

        if (!room.getOwnerEmail().equals(email)) {
            throw new RuntimeException("Not allowed");
        }

        room.setTitle(dto.getTitle());
        room.setLocation(dto.getLocation());
        room.setRent(dto.getRent());
        room.setDescription(dto.getDescription());
        room.setRoomType(dto.getRoomType());
        // ✅ CLEAR OLD IMAGES FIRST (VERY IMPORTANT)
        room.getImages().clear();

        if (dto.getImages() != null) {

            for (String url : dto.getImages()) {

                RoomImage image = new RoomImage();
                image.setImageUrl(url);
                image.setRoom(room); // ✅ FIX

                room.getImages().add(image);
            }
        }

        Room updated = roomRepository.save(room);
        return mapToDTO(updated);
    }

    // SEARCH
    public List<RoomResponseDTO> searchFilter(
            String location,
            String roomType,
            Double minRent,
            Double maxRent
    ) {
        return roomRepository.findAll()
                .stream()
                .filter(r -> location == null || r.getLocation().toLowerCase().contains(location.toLowerCase()))
                .filter(r -> roomType == null || r.getRoomType().equalsIgnoreCase(roomType))
                .filter(r -> minRent == null || r.getRent() >= minRent)
                .filter(r -> maxRent == null || r.getRent() <= maxRent)
                .map(this::mapToDTO)
                .toList();
    }

    // DTO MAPPER
    private RoomResponseDTO mapToDTO(Room room) {

        RoomResponseDTO dto = new RoomResponseDTO();

        dto.setId(room.getId());
        dto.setTitle(room.getTitle());
        dto.setLocation(room.getLocation());
        dto.setRent(room.getRent());
        dto.setDescription(room.getDescription());
        dto.setOwnerEmail(room.getOwnerEmail());
        dto.setRoomType(room.getRoomType());
        dto.setAverageRating(
                reviewRepository.getAverageRating(room.getId()) != null
                        ? reviewRepository.getAverageRating(room.getId())
                        : 0.0
        );
        List<String> imgs = room.getImages()
                .stream()
                .map(RoomImage::getImageUrl)
                .toList();

        dto.setImages(imgs);

        return dto;
    }
}