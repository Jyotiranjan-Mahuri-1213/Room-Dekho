package com.roomdekho.ownerrequest;

import jakarta.persistence.*;

@Entity
@Table(name = "owner_requests")
public class OwnerRequest {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    // User who requested owner role
    @Column(nullable = false)
    private String email;



    // Personal Details

    private String name;

    private String phone;

    private String address;



    // Room Details

    private String roomTitle;

    private String roomType;

    private String location;

    private Double rent;

    @Column(length = 1000)
    private String description;



    // Room Images (URL for now)

    // Room Images
    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String image1;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String image2;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String image3;



    // PENDING / APPROVED / REJECTED

    private String status;



    public OwnerRequest() {
    }



    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }



    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }



    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }



    public String getPhone() {
        return phone;
    }


    public void setPhone(String phone) {
        this.phone = phone;
    }



    public String getAddress() {
        return address;
    }


    public void setAddress(String address) {
        this.address = address;
    }



    public String getRoomTitle() {
        return roomTitle;
    }


    public void setRoomTitle(String roomTitle) {
        this.roomTitle = roomTitle;
    }



    public String getRoomType() {
        return roomType;
    }


    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }



    public String getLocation() {
        return location;
    }


    public void setLocation(String location) {
        this.location = location;
    }



    public Double getRent() {
        return rent;
    }


    public void setRent(Double rent) {
        this.rent = rent;
    }



    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }



    public String getImage1() {
        return image1;
    }


    public void setImage1(String image1) {
        this.image1 = image1;
    }



    public String getImage2() {
        return image2;
    }


    public void setImage2(String image2) {
        this.image2 = image2;
    }



    public String getImage3() {
        return image3;
    }


    public void setImage3(String image3) {
        this.image3 = image3;
    }



    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        this.status = status;
    }

}