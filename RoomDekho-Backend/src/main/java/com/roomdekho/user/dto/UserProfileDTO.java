package com.roomdekho.user.dto;

public class UserProfileDTO {

    private String name;
    private String email;
    private String phone;
    private String role;
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // getters and setters
}