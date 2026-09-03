package com.roomdekho.admin.dto;

public class AdminStatsDTO {

    private long users;
    private long rooms;
    private long bookings;
    private long ownerrequests;

    public AdminStatsDTO(
            long users,
            long rooms,
            long bookings
    ) {
        this.users = users;
        this.rooms = rooms;
        this.bookings = bookings;
    }

    public long getUsers() {
        return users;
    }

    public long getRooms() {
        return rooms;
    }

    public long getBookings() {
        return bookings;
    }

    public long getOwnerrequests() {
        return ownerrequests;
    }

    public void setOwnerrequests(long ownerrequests) {
        this.ownerrequests = ownerrequests;
    }
}