package com.roomdekho;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RoomDekhoBackendApplication {

	public static void main(String[] args) {

		SpringApplication.run(RoomDekhoBackendApplication.class, args);


		System.out.println("       ROOM DEKHO BACKEND STARTED");
		System.out.println("......................................");
		System.out.println("Server   : http://localhost:8080");
		System.out.println("Database : room_dekho_db Connected");
		System.out.println("Status   : done");


	}

}