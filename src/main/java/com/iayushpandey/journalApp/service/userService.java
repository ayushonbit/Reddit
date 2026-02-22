package com.iayushpandey.journalApp.service;

import com.iayushpandey.journalApp.Entity.JournalEntry;
import com.iayushpandey.journalApp.Entity.User;
import com.iayushpandey.journalApp.repository.JournalEntryRepository;
import com.iayushpandey.journalApp.repository.userRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class userService {

    @Autowired
    private userRepository userRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public void saveEntry(User user){
        userRepository.save(user);

    }

    public void saveNewUser(User user){
       user.setPassword(passwordEncoder.encode(user.getPassword()));
       user.setRoles(Arrays.asList("User"));
       userRepository.save(user);

    }

    public List<User> getAll(){
       return userRepository.findAll();

    }

    public Optional<User> findById(ObjectId id){
        return userRepository.findById(id);

    }

    public User findByUserName(String id){
        return userRepository.findByUserName(id);

    }

    public void deleteById(ObjectId id){
        userRepository.deleteById(id);
    }










}
