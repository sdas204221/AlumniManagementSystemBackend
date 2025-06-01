package com.sdas204221.AlumniManagementSystem.repository;


import com.sdas204221.AlumniManagementSystem.model.EventRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface EventRegistrationRepository extends JpaRepository<EventRegistration, Long> {}
