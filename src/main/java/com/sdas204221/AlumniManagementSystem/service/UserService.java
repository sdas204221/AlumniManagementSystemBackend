package com.sdas204221.AlumniManagementSystem.service;

import com.sdas204221.AlumniManagementSystem.model.FileEntity;
import com.sdas204221.AlumniManagementSystem.model.Profile;
import com.sdas204221.AlumniManagementSystem.model.User;
import com.sdas204221.AlumniManagementSystem.repository.FileEntityRepository;
import com.sdas204221.AlumniManagementSystem.repository.ProfileRepository;
import com.sdas204221.AlumniManagementSystem.repository.RoleRepository;
import com.sdas204221.AlumniManagementSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private FileEntityRepository fileEntityRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public List<String> getAllUsername(){
        return userRepository.findAll().stream().map(User::getUsername).toList();
    }
    public User findUser(String username){
        return userRepository.findByUsername(username);
    }
    public void saveProfile(Profile profile){
        if (profile.getUser()!=null)
            saveUser(profile.getUser());
        if (profile.getProfilePicture()!=null)
            saveProfilePicture(profile.getProfilePicture());
        profileRepository.save(profile);
    }

    private void saveProfilePicture(FileEntity profilePicture) {
        fileEntityRepository.save(profilePicture);
    }

    private void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }

    public void registerUser(Profile profile) {
        profile.getUser().setEnabled(false);
        profile.getUser().setAccountLocked(false);
        saveProfile(profile);
    }
    public Profile getProfile(String username){
        User user=findUser(username);
        Profile profile=profileRepository.findByUser(user);
        return profile;
    }
    public List<Profile> getPendingUsers(){
        List<User> users=userRepository.findByIsEnabledFalse();
        List<Profile> profiles=new ArrayList<>();
        for (User user:users){
            profiles.add(profileRepository.findByUser(user));
        }
        return profiles;
    }
}
