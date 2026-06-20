package com.roomdekho.admin;

import com.roomdekho.room.Room;
import com.roomdekho.room.RoomRepository;
import com.roomdekho.user.User;
import com.roomdekho.user.UserRepository;
import com.roomdekho.jwt.JwtService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final JwtService jwtService;

    public AdminService(AdminRepository adminRepository,
                        UserRepository userRepository,
                        RoomRepository roomRepository,
                        JwtService jwtService) {

        this.adminRepository = adminRepository;
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
        this.jwtService = jwtService;
    }


    public String login(String email, String password) {

        return adminRepository.findByEmail(email)
                .filter(admin -> admin.getPassword().equals(password))
                .map(admin -> jwtService.generateToken(admin.getEmail(), "ADMIN"))
                .orElse(null);
    }

    //  users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // rooms
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public void deleteRoom(Long roomId) {
        roomRepository.deleteById(roomId);
    }
}