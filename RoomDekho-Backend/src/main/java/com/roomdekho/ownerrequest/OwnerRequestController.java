package com.roomdekho.ownerrequest;


import com.roomdekho.ownerrequest.dto.OwnerRequestDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/owner-requests")
@CrossOrigin
public class OwnerRequestController {


    private final OwnerRequestService service;


    public OwnerRequestController(
            OwnerRequestService service
    ){

        this.service = service;

    }





    // =====================================
    // USER SEND OWNER REQUEST
    // =====================================

    @PostMapping("/requests")
    public OwnerRequest createRequest(
            @RequestBody OwnerRequestDTO dto
    ){

        return service.createRequest(dto);

    }







    // =====================================
    // VIEW ALL REQUESTS
    // ADMIN FUTURE
    // =====================================

    @GetMapping("/all")
    public List<OwnerRequest> getAllRequests(){

        return service.getAllRequests();

    }





    @GetMapping("/pending")
    public List<OwnerRequest> getPendingRequests() {
        return service.getPendingRequests();
    }

    // =====================================
    // APPROVE REQUEST
    // ADMIN FUTURE
    // =====================================

    @PutMapping("/{id}/approve")
    public OwnerRequest approveRequest(
            @PathVariable Long id
    ){

        return service.approveRequest(id);

    }







    // =====================================
    // REJECT REQUEST
    // ADMIN FUTURE
    // =====================================

    @PutMapping("/{id}/reject")
    public OwnerRequest rejectRequest(
            @PathVariable Long id
    ){

        return service.rejectRequest(id);

    }


}