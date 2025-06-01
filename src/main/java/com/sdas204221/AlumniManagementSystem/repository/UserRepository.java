package com.sdas204221.AlumniManagementSystem.repository;
import com.sdas204221.AlumniManagementSystem.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    List<User> findByIsEnabledFalse();
}