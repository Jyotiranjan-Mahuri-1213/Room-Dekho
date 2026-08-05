package com.roomdekho.admin;


import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;



@Component
public class AdminDataLoader implements CommandLineRunner {


    private final AdminRepository adminRepository;

    private final PasswordEncoder passwordEncoder;



    public AdminDataLoader(
            AdminRepository adminRepository,
            PasswordEncoder passwordEncoder
    ){

        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;

    }



    @Override
    public void run(String... args) throws Exception {


        if(adminRepository.findByEmail("admin@gmail.com").isEmpty()){


            Admin admin = new Admin();


            admin.setEmail("admin@gmail.com");


            admin.setPassword(
                    passwordEncoder.encode("admin123")
            );


            admin.setRole("ADMIN");



            adminRepository.save(admin);


            System.out.println(
                    "ADMIN CREATED : admin@gmail.com / admin123"
            );

        }

    }

}