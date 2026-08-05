package com.roomdekho.ownerrequest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java .util.List;

@Repository
public interface OwnerRequestRepository
        extends JpaRepository<OwnerRequest, Long> {
    List<OwnerRequest> findByStatus(String status);

}