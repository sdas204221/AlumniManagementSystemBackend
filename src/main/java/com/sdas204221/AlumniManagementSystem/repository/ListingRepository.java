package com.sdas204221.AlumniManagementSystem.repository;

import com.sdas204221.AlumniManagementSystem.model.Listing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListingRepository extends JpaRepository<Listing,Long> {
    List<Listing> findByApprovedTrue();
    List<Listing> findByApprovedFalse();
}
