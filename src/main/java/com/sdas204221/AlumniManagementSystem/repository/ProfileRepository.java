package com.sdas204221.AlumniManagementSystem.repository;
import com.sdas204221.AlumniManagementSystem.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Profile findByUser(User user);
}
