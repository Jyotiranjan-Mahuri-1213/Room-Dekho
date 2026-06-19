package com.roomdekho.room.image.DTO;


public class RoomImageResponseDTO {

    private Long id;

    private String imageUrl;

    private Long roomId;


    public RoomImageResponseDTO(
            Long id,
            String imageUrl,
            Long roomId
    ){
        this.id = id;
        this.imageUrl = imageUrl;
        this.roomId = roomId;
    }


    public Long getId() {
        return id;
    }


    public String getImageUrl() {
        return imageUrl;
    }


    public Long getRoomId() {
        return roomId;
    }
}