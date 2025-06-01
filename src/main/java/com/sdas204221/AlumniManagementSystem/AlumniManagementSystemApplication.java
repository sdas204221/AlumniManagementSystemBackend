package com.sdas204221.AlumniManagementSystem;

import com.sdas204221.AlumniManagementSystem.model.Role;
import com.sdas204221.AlumniManagementSystem.model.User;
import com.sdas204221.AlumniManagementSystem.repository.RoleRepository;
import com.sdas204221.AlumniManagementSystem.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class AlumniManagementSystemApplication {

	public static void main(String[] args) {

		ApplicationContext context= SpringApplication.run(AlumniManagementSystemApplication.class, args);
//		RoleRepository roleRepository=context.getBean(RoleRepository.class);
//		roleRepository.save(new Role(0,"admin"));
//		roleRepository.save(new Role(1,"moderator"));
//		roleRepository.save(new Role(2,"user"));

//		UserRepository userRepository=context.getBean(UserRepository.class);

//		PasswordEncoder passwordEncoder=context.getBean(PasswordEncoder.class);
//		List<Role> roles1=new ArrayList<>();
//		roles1.add(new Role("alumni"));
//		roles1.add(new Role("mod"));
//		roles1.add(new Role("admin"));
//		roleRepository.saveAll(roles1);
//		List<Role> roles=new ArrayList<>();
//		roles.add(roleRepository.findByName("alumni"));
//		roles.add(roleRepository.findByName("mod"));
//		roles.add(roleRepository.findByName("admin"));
//		User user1 = new User(null, "john_doe", passwordEncoder.encode("password123"), "john@example.com", roles, false, true);
//		userRepository.save(user1);
//		roles.remove(0);
//		User user2 = new User(null, "jane_doe", passwordEncoder.encode("securePass"), "jane@example.com",roles, false, true);
//		userRepository.save(user2);
//
		System.out.println("Server Started");
	}

}
