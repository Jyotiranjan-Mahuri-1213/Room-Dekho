package com.roomdekho.admin;

import com.roomdekho.room.Room;
import com.roomdekho.user.User;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    //  ADMIN LOGIN
    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password) {

        String token = adminService.login(email, password);

        return token != null ? token : "Invalid credentials";
    }

    //  ALL USERS
    @GetMapping("/users")
    public List<User> getUsers() {
        return adminService.getAllUsers();
    }

    //  ALL ROOMS
    @GetMapping("/rooms")
    public List<Room> getRooms() {
        return adminService.getAllRooms();
    }

    //  DELETE ROOM
    @DeleteMapping("/rooms/{id}")
    public String deleteRoom(@PathVariable Long id) {
        adminService.deleteRoom(id);
        return "Room deleted by admin";
    }
}