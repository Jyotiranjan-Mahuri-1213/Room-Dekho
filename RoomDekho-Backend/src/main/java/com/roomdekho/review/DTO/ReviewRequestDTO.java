package com.roomdekho.review.DTO;


public class ReviewRequestDTO {


    private Long roomId;

    private int rating;

    private String comment;



    public Long getRoomId() {
        return roomId;
    }


    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }


    public int getRating() {
        return rating;
    }


    public void setRating(int rating) {
        this.rating = rating;
    }


    public String getComment() {
        return comment;
    }


    public void setComment(String comment) {
        this.comment = comment;
    }

}