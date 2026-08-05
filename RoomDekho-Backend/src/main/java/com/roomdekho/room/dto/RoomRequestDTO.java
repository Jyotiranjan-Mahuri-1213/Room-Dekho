package com.roomdekho.room.dto;


import java.util.List;


public class RoomRequestDTO {


    private String title;

    private String location;

    private double rent;

    private String description;
    private String roomType;
    private List<String> images;



    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }



    public String getLocation() {
        return location;
    }


    public void setLocation(String location) {
        this.location = location;
    }



    public double getRent() {
        return rent;
    }


    public void setRent(double rent) {
        this.rent = rent;
    }



    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }



    public List<String> getImages() {
        return images;
    }


    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

}