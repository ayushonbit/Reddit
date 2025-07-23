package com.iayushpandey.journalApp.service;

import com.iayushpandey.journalApp.Entity.JournalEntry;
import com.iayushpandey.journalApp.Entity.User;
import com.iayushpandey.journalApp.repository.JournalEntryRepository;
import com.iayushpandey.journalApp.repository.userRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class userService {

    @Autowired
    private userRepository userRepository;

    public void saveEntry(User user){
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
