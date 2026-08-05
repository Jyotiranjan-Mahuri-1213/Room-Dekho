package com.roomdekho.room.image.dto;


public class RoomImageResponseDTO {


    private Long id;


    private String imageUrl;


    private Long roomId;




    public RoomImageResponseDTO(){

    }




    public Long getId() {

        return id;

    }



    public void setId(Long id) {

        this.id = id;

    }




    public String getImageUrl() {

        return imageUrl;

    }



    public void setImageUrl(String imageUrl) {

        this.imageUrl = imageUrl;

    }




    public Long getRoomId() {

        return roomId;

    }



    public void setRoomId(Long roomId) {

        this.roomId = roomId;

    }


}