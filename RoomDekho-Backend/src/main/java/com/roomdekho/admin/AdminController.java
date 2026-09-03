package com.roomdekho.admin;

import com.roomdekho.admin.dto.AdminStatsDTO;
import com.roomdekho.admin.dto.AdminLoginRequestDTO;
import com.roomdekho.admin.dto.AdminLoginResponseDTO;
import com.roomdekho.user.User;
import com.roomdekho.room.Room;
import org.springframework.web.bind.annotation.*;
import com.roomdekho.booking.Booking;
import java.util.List;



@RestController
@RequestMapping("/api/admin")
@CrossOrigin
public class AdminController {


    private final AdminService adminService;



    public AdminController(
            AdminService adminService
    ){

        this.adminService = adminService;

    }





    @PostMapping("/login")
    public AdminLoginResponseDTO login(
            @RequestBody AdminLoginRequestDTO request
    ){

        return adminService.login(request);

    }






    @GetMapping("/users")
    public List<User> getAllUsers(){

        return adminService.getAllUsers();

    }





    @DeleteMapping("/users/{id}")
    public String deleteUser(
            @PathVariable Long id
    ){

        return adminService.deleteUser(id);

    }



    @GetMapping("/rooms")
    public List<Room> getAllRooms(){

        return adminService.getAllRooms();

    }



// =========================
// DELETE ROOM
// =========================

    @DeleteMapping("/rooms/{id}")
    public String deleteRoom(
            @PathVariable Long id
    ){

        return adminService.deleteRoom(id);

    }

    // =========================
// GET ALL BOOKINGS
// =========================

    @GetMapping("/bookings")
    public List<Booking> getAllBookings(){

        return adminService.getAllBookings();

    }

    @GetMapping("/stats")
    public AdminStatsDTO getStats() {

        return adminService.getStats();

    }

}