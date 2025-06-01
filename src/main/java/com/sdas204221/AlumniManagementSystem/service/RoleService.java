package com.sdas204221.AlumniManagementSystem.service;

import com.sdas204221.AlumniManagementSystem.model.Role;
import com.sdas204221.AlumniManagementSystem.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public Role getRole(String role){
            return roleRepository.findByName(role.toLowerCase());
    }
}
