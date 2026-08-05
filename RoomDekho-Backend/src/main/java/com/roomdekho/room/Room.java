package com.roomdekho.room;


import com.roomdekho.room.image.RoomImage;

import jakarta.persistence.*;

import java.util.List;
import java.util.ArrayList;



@Entity
@Table(name = "rooms")
public class Room {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String title;


    private String location;


    private double rent;


    private String description;


    private String ownerEmail;
    private String roomType;


    // Multiple images for one room
    @OneToMany(
            mappedBy = "room",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<RoomImage> images = new ArrayList<>();



    public Room(){}



    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }



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



    public String getOwnerEmail() {
        return ownerEmail;
    }


    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public List<RoomImage> getImages() {
        return images;
    }


    public void setImages(List<RoomImage> images) {

        this.images.clear();

        if(images != null){

            for(RoomImage image : images){

                image.setRoom(this);

                this.images.add(image);

            }
        }
    }



}