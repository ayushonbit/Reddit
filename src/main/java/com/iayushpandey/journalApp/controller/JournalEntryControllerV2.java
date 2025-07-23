package com.iayushpandey.journalApp.controller;


import com.iayushpandey.journalApp.Entity.JournalEntry;
import com.iayushpandey.journalApp.Entity.User;
import com.iayushpandey.journalApp.service.JournalEntryService;
import com.iayushpandey.journalApp.service.userService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private userService userService;


    //    @GetMapping("/journal-entries") Í
//    public List<JournalEntry> getAllJournalEntriesOfUser(){
//        //Methods inside a controller class should be public so that they can be accesed and invoked by the Spring framework or external http requests
//        return journalEntryService.getAllJournalEntriesOfUser();
//    }
@GetMapping("{userName}")  // Remove the space
public ResponseEntity<?> getAllJournalEntriesOfUser(@PathVariable String userName) {
    User user = userService.findByUserName(userName);
    List<JournalEntry>all = user.getJournalEntries();
    if(all!=null && !all.isEmpty()){
        return new ResponseEntity<>(all, HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
}

    @PostMapping("{userName}")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry, @PathVariable String userName){
        // we will parse the endpoint name in body - raw - JSON in the post request allowing the server to parse and process the incoming data accurately
        try {
            journalEntryService.saveEntry(myEntry, userName);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED) ;
        }catch (Exception e){
            return new ResponseEntity<>(myEntry, HttpStatus.BAD_REQUEST) ;
        }

    }
    @GetMapping("/id/{myid}")
    public ResponseEntity<?> getJournalById(@PathVariable ObjectId myid){
        Optional<JournalEntry>journalEntry = journalEntryService.findById(myid);
        if(journalEntry.isPresent()){
            return new ResponseEntity<>(journalEntry, HttpStatus.OK);
        }
        return new ResponseEntity<>( HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/id/{userName}/{myid}")
    public ResponseEntity<?> deleteEntryByid(@PathVariable ObjectId myid, @PathVariable String userName){
         journalEntryService.deleteById(myid, userName);
         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/id/{userName}/{myid}")
    public ResponseEntity<?>  updateJournalById(@PathVariable ObjectId myid, @RequestBody JournalEntry newEntry, @PathVariable String userName){
        JournalEntry old = journalEntryService.findById(myid).orElse(null);
        if(old != null){
            old.setTitle(newEntry.getTitle()!=null && !newEntry.getTitle().equals("")? newEntry.getTitle():old.getTitle());
            old.setContent(newEntry.getContent()!=null && !newEntry.getContent().equals("")? newEntry.getContent():old.getContent());
            journalEntryService.saveEntry(old);
            return new ResponseEntity<>(old, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



}
