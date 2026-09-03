package com.roomdekho.config;

import com.roomdekho.room.Room;
import com.roomdekho.room.RoomRepository;
import com.roomdekho.room.image.RoomImage;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RoomDataSeeder implements CommandLineRunner {

    private final RoomRepository roomRepository;

    public RoomDataSeeder(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public void run(String... args) {

        // Check existing rooms
        long existingRooms = roomRepository.count();

        // Stop if 100 rooms already exist
        if (existingRooms >= 100) {
            System.out.println("100 or more rooms already exist. Seeder skipped.");
            return;
        }

        // Create only the number needed to reach 100
        int roomsToCreate = (int) (100 - existingRooms);

        System.out.println("Existing rooms: " + existingRooms);
        System.out.println("Creating " + roomsToCreate + " demo rooms...");

        List<Room> rooms = new ArrayList<>();

        // ================= LOCATIONS =================

        String[] locations = {
                "Patia",
                "KIIT Square",
                "Infocity",
                "Chandrasekharpur",
                "Jayadev Vihar",
                "Saheed Nagar",
                "Khandagiri",
                "Rasulgarh",
                "Cuttack",
                "Puri"
        };

        // ================= ROOM TYPES =================

        String[] roomTypes = {
                "Single Room",
                "PG",
                "1BHK",
                "2BHK",
                "Shared Room",
                "Studio"
        };

        // ================= TITLES =================

        String[] titles = {
                "Comfortable Room Near Main Road",
                "Fully Furnished Room",
                "Premium Room Near College",
                "Affordable Student Room",
                "Modern Room With Balcony",
                "Spacious Room For Students",
                "Peaceful Room In Prime Location",
                "Well Furnished PG Room"
        };

        // ================= IMAGES =================

        String[] images = {
                "https://images.unsplash.com/photo-1522708323590-d24dbb6b0267",
                "https://images.unsplash.com/photo-1505693416388-ac5ce068fe85",
                "https://images.unsplash.com/photo-1560185008-b033106af5c3",
                "https://images.unsplash.com/photo-1560448204-e02f11c3d0e2",
                "https://images.unsplash.com/photo-1560185127-6a8c5f7b6b6b"
        };

        // ================= CREATE ROOMS =================

        for (int i = 1; i <= roomsToCreate; i++) {

            Room room = new Room();

            String location =
                    locations[(i - 1) % locations.length];

            String roomType =
                    roomTypes[(i - 1) % roomTypes.length];

            String title =
                    titles[(i - 1) % titles.length];

            room.setTitle(
                    title + " - " + location
            );

            room.setLocation(location);

            // Rent between ₹5,000 and ₹15,000
            room.setRent(
                    5000 + ((i * 500) % 10000)
            );

            room.setRoomType(roomType);

            room.setDescription(
                    "Comfortable " + roomType + " available in " + location + ". Suitable for students and working professionals. " +
                            "Located near shops, transportation and other important facilities."
            );

            room.setOwnerEmail(
                    "demo.owner" + i + "@roomdekho.com"
            );

            // ================= IMAGES =================

            List<RoomImage> roomImages =
                    new ArrayList<>();

            // Add 3 images per room
            for (int j = 0; j < 3; j++) {

                RoomImage image = new RoomImage();

                image.setImageUrl(
                        images[(i + j) % images.length]
                );

                image.setRoom(room);

                roomImages.add(image);
            }

            room.setImages(roomImages);

            rooms.add(room);
        }

        // ================= SAVE =================

        roomRepository.saveAll(rooms);

        System.out.println("====================================");
        System.out.println(roomsToCreate + " demo rooms created successfully!");
        System.out.println("====================================");
    }
}