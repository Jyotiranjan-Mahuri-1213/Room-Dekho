package com.roomdekho.review;


import com.roomdekho.review.DTO.ReviewRequestDTO;
import com.roomdekho.review.DTO.ReviewResponseDTO;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/reviews")
@CrossOrigin
public class ReviewController {


    private final ReviewService reviewService;


    public ReviewController(ReviewService reviewService){

        this.reviewService = reviewService;

    }



    // Add Review
    @PostMapping
    public ReviewResponseDTO addReview(
            @RequestBody ReviewRequestDTO dto
    ){

        String email =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal()
                        .toString();


        return reviewService.addReview(dto,email);

    }



    // Get reviews of a room
    @GetMapping("/room/{roomId}")
    public List<ReviewResponseDTO> getRoomReviews(
            @PathVariable Long roomId
    ){

        return reviewService.getRoomReviews(roomId);

    }



    // Get my reviews
    @GetMapping("/my")
    public List<ReviewResponseDTO> getMyReviews(){

        String email =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal()
                        .toString();


        return reviewService.getMyReviews(email);

    }

}