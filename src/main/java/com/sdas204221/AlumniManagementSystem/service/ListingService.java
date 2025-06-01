package com.sdas204221.AlumniManagementSystem.service;

import com.sdas204221.AlumniManagementSystem.model.Listing;
import com.sdas204221.AlumniManagementSystem.repository.ListingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListingService {
    @Autowired
    private ListingRepository listingRepository;
    public Listing save(Listing listing){
         return listingRepository.save(listing);
    }

    public Listing fildListing(Long postId) {
        return listingRepository.findById(postId).get();
    }
    public List<Listing> getAllApproved(){
        return listingRepository.findByApprovedTrue();
    }
    public List<Listing> getAllNotApproved(){
        return listingRepository.findByApprovedFalse();
    }
}
