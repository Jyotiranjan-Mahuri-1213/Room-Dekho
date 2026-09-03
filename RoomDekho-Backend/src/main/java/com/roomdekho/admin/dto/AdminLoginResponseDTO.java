package com.roomdekho.admin.dto;


public class AdminLoginResponseDTO {


    private String token;


    private Long adminId;


    private String email;


    private String role;



    public AdminLoginResponseDTO(){

    }



    public AdminLoginResponseDTO(
            String token,
            Long adminId,
            String email,
            String role
    ){

        this.token = token;
        this.adminId = adminId;
        this.email = email;
        this.role = role;

    }



    public String getToken() {
        return token;
    }



    public Long getAdminId() {
        return adminId;
    }


    public String getEmail() {
        return email;
    }



    public String getRole() {
        return role;
    }

}