package com.iayushpandey.journalApp.service;

import com.iayushpandey.journalApp.Entity.JournalEntry;
import com.iayushpandey.journalApp.Entity.User;
import com.iayushpandey.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private userService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName){
        User user = userService.findByUserName(userName);
        journalEntry.setDate(LocalDateTime.now());
        JournalEntry saved = journalEntryRepository.save(journalEntry);
        user.getJournalEntries().add(saved);
        userService.saveEntry(user);

    }

    public void saveEntry(JournalEntry journalEntry){
    journalEntryRepository.save(journalEntry);


    }

    public List<JournalEntry> getAll(){
       return journalEntryRepository.findAll();

    }

    public Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepository.findById(id);

    }

    public void deleteById(ObjectId id, String userName){
        User user = userService.findByUserName(userName);
        user.getJournalEntries().removeIf(x->x.getId().equals(id)); // casscade delete
        userService.saveEntry(user); // yaha updated user save ho jaega
        journalEntryRepository.deleteById(id);
    }










}
