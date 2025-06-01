package com.sdas204221.AlumniManagementSystem.service;

import com.sdas204221.AlumniManagementSystem.model.User;
import com.sdas204221.AlumniManagementSystem.model.UserPrincipal;
import com.sdas204221.AlumniManagementSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user= userRepository.findByUsername(username);

        if (user==null) {
            throw new UsernameNotFoundException("User 404");
        }
        return new UserPrincipal(user);
    }

}