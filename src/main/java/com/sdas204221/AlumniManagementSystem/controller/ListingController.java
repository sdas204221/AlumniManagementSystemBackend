package com.sdas204221.AlumniManagementSystem.controller;

import com.sdas204221.AlumniManagementSystem.model.Listing;
import com.sdas204221.AlumniManagementSystem.model.User;
import com.sdas204221.AlumniManagementSystem.service.ListingService;
import com.sdas204221.AlumniManagementSystem.service.RoleService;
import com.sdas204221.AlumniManagementSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/listing")
public class ListingController {
    @Autowired
    private ListingService listingService;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @PostMapping
    public ResponseEntity<Long> add(@RequestBody Listing listing, @AuthenticationPrincipal UserDetails userDetails){
        listing.setUsername(userDetails.getUsername());
        listing.setCreationDate(LocalDateTime.now());
        listing.setApproved(false);
        listing=listingService.save(listing);
        return new ResponseEntity<>(listing.getId(),HttpStatus.CREATED);
    }

    @PatchMapping("/approve/{postId}")
    public ResponseEntity<?> approve(@PathVariable Long postId,@AuthenticationPrincipal UserDetails userDetails){
        User user=userService.findUser(userDetails.getUsername());
        if (
                user.getRoles().contains(roleService.getRole("admin"))
                        ||
                        user.getRoles().contains(roleService.getRole("moderator"))
        ){
                Listing listing=listingService.fildListing(postId);
                listing.setApproved(true);
                listing.setApprovalDate(LocalDateTime.now());
                listingService.save(listing);
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping
    public ResponseEntity<List<Listing>> approvedListing(){
        return new ResponseEntity<>(listingService.getAllApproved(),HttpStatus.OK);
    }
    @GetMapping("/unapproved")
    public ResponseEntity<List<Listing>> unapprovedListing(@AuthenticationPrincipal UserDetails userDetails){
        User user=userService.findUser(userDetails.getUsername());
        if (user.getRoles().contains(roleService.getRole("admin"))
                ||
                user.getRoles().contains(roleService.getRole("moderator"))){
            return new ResponseEntity<>(listingService.getAllNotApproved(),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

}
