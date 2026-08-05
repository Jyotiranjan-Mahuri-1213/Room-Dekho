package com.roomdekho.ownerrequest;

import com.roomdekho.ownerrequest.dto.OwnerRequestDTO;
import com.roomdekho.user.User;
import com.roomdekho.user.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerRequestService {

    private final OwnerRequestRepository repository;
    private final UserRepository userRepository;

    public OwnerRequestService(
            OwnerRequestRepository repository,
            UserRepository userRepository
    ){
        this.repository = repository;
        this.userRepository = userRepository;
    }

    // =========================
    // CREATE REQUEST
    // =========================
    public OwnerRequest createRequest(OwnerRequestDTO dto) {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        OwnerRequest request = new OwnerRequest();

        request.setEmail(email);
        request.setName(dto.getName());
        request.setPhone(dto.getPhone());
        request.setAddress(dto.getAddress());

        request.setRoomTitle(dto.getRoomTitle());
        request.setRoomType(dto.getRoomType());
        request.setLocation(dto.getLocation());
        request.setRent(dto.getRent());
        request.setDescription(dto.getDescription());

        request.setImage1(dto.getImage1());
        request.setImage2(dto.getImage2());
        request.setImage3(dto.getImage3());

        request.setStatus("PENDING");

        return repository.save(request);
    }

    // =========================
    // GET ALL REQUESTS
    // =========================
    public List<OwnerRequest> getAllRequests() {
        return repository.findAll();
    }

    // =========================
    // GET PENDING
    // =========================
    public List<OwnerRequest> getPendingRequests() {
        return repository.findByStatus("PENDING");
    }

    // =========================
    // APPROVE REQUEST
    // =========================
    public OwnerRequest approveRequest(Long id) {

        OwnerRequest request = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        request.setStatus("APPROVED");
        repository.save(request);

        // 🔥 USER → OWNER ROLE UPDATE
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setRole("OWNER");
        userRepository.save(user);

        return request;
    }

    // =========================
    // REJECT REQUEST
    // =========================
    public OwnerRequest rejectRequest(Long id) {

        OwnerRequest request = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        request.setStatus("REJECTED");

        return repository.save(request);
    }
}