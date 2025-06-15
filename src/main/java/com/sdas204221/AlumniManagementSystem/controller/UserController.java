package com.sdas204221.AlumniManagementSystem.controller;

import com.sdas204221.AlumniManagementSystem.model.Profile;
import com.sdas204221.AlumniManagementSystem.model.Role;
import com.sdas204221.AlumniManagementSystem.model.User;
import com.sdas204221.AlumniManagementSystem.service.JwtService;
import com.sdas204221.AlumniManagementSystem.service.RoleService;
import com.sdas204221.AlumniManagementSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/user")
public class UserController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RoleService roleService;

    @GetMapping("/{username}")
    public ResponseEntity<Profile> getUser(@PathVariable String username){
        Profile profile=userService.getProfile(username);
        return ResponseEntity.ok(profile);

    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody User user) {
        System.out.println(user.getUsername());
        System.out.println(passwordEncoder.encode(user.getPassword()));
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
            );

            if (authentication.isAuthenticated()) {
                User original = userService.findUser(user.getUsername());
                Map<String, Object> responseBody = new HashMap<>();
                responseBody.put("jwt", jwtService.generateToken(original.getUsername()));
                responseBody.put("roles", original.getRoles());

                return ResponseEntity.ok(responseBody);
            }
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid credentials"));
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Profile profile){
        try {
            profile.getUser().setRoles(new ArrayList<Role>());
            profile.getUser().getRoles().add(roleService.getRole("user"));
            userService.registerUser(profile);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PatchMapping("/approve/{username}")
    public ResponseEntity<?> approve(@PathVariable String username, @AuthenticationPrincipal UserDetails userDetails){
        if(userDetails
                .getAuthorities()
                .contains(
                        new SimpleGrantedAuthority(
                                roleService
                                        .getRole("admin")
                                        .getName()
                        )
                )
                ||
                userDetails
                        .getAuthorities()
                        .contains(
                                new SimpleGrantedAuthority(
                                        roleService
                                                .getRole("moderator")
                                                .getName()
                                )
                        )
        ){
            User user=userService.findUser(username);
            user.setEnabled(true);
            userService.updateUser(user);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        }
    @GetMapping("/pending")
    public ResponseEntity<List<Profile>> getPendingUsers( @AuthenticationPrincipal UserDetails userDetails){
        if(userDetails
                .getAuthorities()
                .contains(
                        new SimpleGrantedAuthority(
                                roleService
                                        .getRole("admin")
                                        .getName()
                        )
                )
                ||
                userDetails
                        .getAuthorities()
                        .contains(
                                new SimpleGrantedAuthority(
                                        roleService
                                                .getRole("moderator")
                                                .getName()
                                )
                        )
        ){
        List<Profile> profiles=userService.getPendingUsers();
            return new ResponseEntity<>(profiles,HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
    @PatchMapping("/role/{username}")
    public ResponseEntity<?> changeAuthority(
            @PathVariable String username,
            @RequestBody List<String> authorities,
            @AuthenticationPrincipal UserDetails userDetails){
        if(userDetails
                .getAuthorities()
                .contains(
                        new SimpleGrantedAuthority(
                                roleService
                                        .getRole("admin")
                                        .getName()
                        )
                )
                ||
                userDetails
                        .getAuthorities()
                        .contains(
                                new SimpleGrantedAuthority(
                                        roleService
                                                .getRole("moderator")
                                                .getName()
                                )
                        )
        ){
            User user=userService.findUser(username);
            int authorityLevel=userDetails
                    .getAuthorities().contains(new SimpleGrantedAuthority(
                            roleService
                                    .getRole("admin")
                                    .getName()
                    ))?0:1;

            for (String roleName:authorities){
                Role role=roleService.getRole(roleName);
                if (role.getId()>=authorityLevel && !user.getRoles().contains(role)){
                    user.getRoles().add(role);
                }
            }

            userService.updateUser(user);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
    @GetMapping("/myProfile")
    public ResponseEntity<Profile> GetMyProfile(@AuthenticationPrincipal UserDetails userDetails){
        Profile profile=userService.getProfile(userDetails.getUsername());
        return new ResponseEntity<>(profile,HttpStatus.OK);
    }
    @PutMapping("/myProfile")
    public ResponseEntity<Profile> SetMyProfile(@RequestBody Profile profile,@AuthenticationPrincipal UserDetails userDetails){
       String username=
               userDetails
                       .getAuthorities().contains(new SimpleGrantedAuthority(
                               roleService
                                       .getRole("admin")
                                       .getName()
                       )) ||
                       userDetails
                               .getAuthorities().contains(new SimpleGrantedAuthority(
                                       roleService
                                               .getRole("moderator")
                                               .getName()
                               ))
        ?profile.getUser().getUsername():userDetails.getUsername();
        profile.getUser().setUsername(username);
       User user=userService.findUser(username);
        profile.getUser().setRoles(user.getRoles());
        userService.saveProfile(profile);
        User user1=userService.findUser(username);
        user1.setEnabled(true);
        userService.updateUser(user1);
        return new ResponseEntity<>(userService.getProfile(username),HttpStatus.OK);
    }
    @GetMapping("/users")
    public ResponseEntity<List<String>> getAllUsername(){
        return new ResponseEntity<>(userService.getAllUsername(),HttpStatus.OK);
    }

}
