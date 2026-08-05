package com.roomdekho.room.dto;

import java.util.List;

public class RoomResponseDTO {


    private Long id;

    private String title;

    private String location;

    private double rent;

    private String description;

    private String ownerEmail;

    private List<String> images;
    private double averageRating;


    public Long getId(){
        return id;
    }


    public void setId(Long id){
        this.id=id;
    }



    public String getTitle(){
        return title;
    }


    public void setTitle(String title){
        this.title=title;
    }



    public String getLocation(){
        return location;
    }


    public void setLocation(String location){
        this.location=location;
    }



    public double getRent(){
        return rent;
    }


    public void setRent(double rent){
        this.rent=rent;
    }



    public String getDescription(){
        return description;
    }


    public void setDescription(String description){
        this.description=description;
    }



    public String getOwnerEmail(){
        return ownerEmail;
    }


    public void setOwnerEmail(String ownerEmail){
        this.ownerEmail=ownerEmail;
    }



    public List<String> getImages(){
        return images;
    }


    public void setImages(List<String> images){
        this.images=images;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }


}