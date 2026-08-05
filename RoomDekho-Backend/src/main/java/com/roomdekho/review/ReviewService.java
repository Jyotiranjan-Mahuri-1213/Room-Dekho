package com.roomdekho.review;


import com.roomdekho.review.DTO.ReviewRequestDTO;
import com.roomdekho.review.DTO.ReviewResponseDTO;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ReviewService {


    private final ReviewRepository reviewRepository;


    public ReviewService(ReviewRepository reviewRepository){

        this.reviewRepository = reviewRepository;

    }



    // Add Review
    public ReviewResponseDTO addReview(
            ReviewRequestDTO dto,
            String email
    ){

        Review review = new Review();

        review.setRoomId(dto.getRoomId());
        review.setRating(dto.getRating());
        review.setComment(dto.getComment());
        review.setUserEmail(email);


        Review saved = reviewRepository.save(review);


        return mapToResponse(saved);

    }



    // Get reviews of a room
    public List<ReviewResponseDTO> getRoomReviews(Long roomId){


        return reviewRepository
                .findByRoomId(roomId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());

    }



    // Get logged-in user's reviews
    public List<ReviewResponseDTO> getMyReviews(String email){


        return reviewRepository
                .findByUserEmail(email)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());

    }



    private ReviewResponseDTO mapToResponse(Review review){


        ReviewResponseDTO dto = new ReviewResponseDTO();


        dto.setId(review.getId());
        dto.setRoomId(review.getRoomId());
        dto.setUserEmail(review.getUserEmail());
        dto.setRating(review.getRating());
        dto.setComment(review.getComment());


        return dto;

    }

    public Double getAverageRating(Long roomId) {
        Double avg = reviewRepository.getAverageRating(roomId);
        return avg != null ? avg : 0.0;
    }

}