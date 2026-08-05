package com.roomdekho.admin;


import com.roomdekho.admin.dto.AdminLoginRequestDTO;
import com.roomdekho.admin.dto.AdminLoginResponseDTO;
import com.roomdekho.jwt.JwtService;
import com.roomdekho.user.User;
import com.roomdekho.user.UserRepository;
import com.roomdekho.room.Room;
import com.roomdekho.room.RoomRepository;
import com.roomdekho.booking.Booking;
import com.roomdekho.booking.BookingRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;



@Service
public class AdminService {


    private final AdminRepository adminRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final BookingRepository bookingRepository;

    public AdminService(
            AdminRepository adminRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService,
            UserRepository userRepository,
            RoomRepository roomRepository,
            BookingRepository bookingRepository
    ){

        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
        this.bookingRepository = bookingRepository;

    }



    // ADMIN LOGIN
    public AdminLoginResponseDTO login(
            AdminLoginRequestDTO request
    ){


        Admin admin =
                adminRepository.findByEmail(request.getEmail())
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Admin not found"
                                )
                        );


        if(!passwordEncoder.matches(
                request.getPassword(),
                admin.getPassword()
        )){

            throw new RuntimeException(
                    "Invalid password"
            );

        }


        String token =
                jwtService.generateToken(
                        admin.getEmail(),
                        "ADMIN"
                );


        return new AdminLoginResponseDTO(
                token,
                admin.getId(),
                admin.getEmail(),
                admin.getRole()
        );

    }



    public List<User> getAllUsers(){

        return userRepository.findAll();

    }







    public String deleteUser(Long id){


        User user =
                userRepository.findById(id)
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "User not found"
                                )
                        );


        userRepository.delete(user);


        return "User deleted successfully";

    }



    public List<Room> getAllRooms(){

        return roomRepository.findAll();

    }





    public String deleteRoom(Long id){


        Room room =
                roomRepository.findById(id)
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Room not found"
                                )
                        );


        roomRepository.delete(room);


        return "Room deleted successfully";

    }


    public List<Booking> getAllBookings(){

        return bookingRepository.findAll();

    }


}