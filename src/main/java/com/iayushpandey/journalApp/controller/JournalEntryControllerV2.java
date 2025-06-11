package com.iayushpandey.journalApp.controller;


import com.iayushpandey.journalApp.Entity.JournalEntry;
import com.iayushpandey.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService;

//    @GetMapping("/journal-entries")
//    public List<JournalEntry> getAll(){
//        //Methods inside a controller class should be public so that they can be accesed and invoked by the Spring framework or external http requests
//        return journalEntryService.getAll();
//    }
@GetMapping("/journal-entries")  // Remove the space
public ResponseEntity<?> getAll() {
    List<JournalEntry>all = journalEntryService.getAll();
    if(all!=null && !all.isEmpty()){
        return new ResponseEntity<>(all, HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
}

    @PostMapping
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry){
        // we will parse the endpoint name in body - raw - JSON in the post request allowing the server to parse and process the incoming data accurately
        try {
            myEntry.setDate(LocalDateTime.now());
            journalEntryService.saveEntry(myEntry);
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

    @DeleteMapping("/id/{myid}")
    public ResponseEntity<?> deleteEntryByid(@PathVariable ObjectId myid){
         journalEntryService.deleteById(myid);
         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/id/{id}")
    public JournalEntry updateJournalById(@PathVariable ObjectId id, @RequestBody JournalEntry newEntry){
        JournalEntry old = journalEntryService.findById(id).orElse(null);
        if(old != null){
            old.setTitle(newEntry.getTitle()!=null && !newEntry.getTitle().equals("")? newEntry.getTitle():old.getTitle());
            old.setContent(newEntry.getContent()!=null && !newEntry.getContent().equals("")? newEntry.getContent():old.getContent());

        }
        journalEntryService.saveEntry(old);
        return old;
    }



}
