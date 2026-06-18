package com.roomdekho.user;
import com.roomdekho.user.dto.LoginRequest;
import com.roomdekho.exception.EmailAlreadyExistsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.roomdekho.jwt.JwtService;
import com.roomdekho.user.dto.LoginResponse;
import com.roomdekho.user.dto.UserResponse;
@Service
public class UserService {


    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;


    public UserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService
    ) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;

    }


    public UserResponse registerUser(User user) {


        if(userRepository.findByEmail(user.getEmail()).isPresent()) {

            throw new EmailAlreadyExistsException(
                    "Email already registered"
            );

        }


        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );


        User savedUser = userRepository.save(user);

        return convertToResponse(savedUser);

    }

    public LoginResponse loginUser(LoginRequest request){


        User user = userRepository
                .findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("User not found")
                );


        if(!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        )){

            throw new RuntimeException("Invalid password");

        }


        String token = jwtService.generateToken(user.getEmail());


        UserResponse userResponse =
                new UserResponse(
                        user.getId(),
                        user.getName(),
                        user.getEmail(),
                        user.getPhone(),
                        user.getRole()
                );


        return new LoginResponse(
                token,
                userResponse
        );

    }
    private UserResponse convertToResponse(User user){

        UserResponse response = new UserResponse();

        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setPhone(user.getPhone());
        response.setRole(user.getRole());

        return response;

    }

}