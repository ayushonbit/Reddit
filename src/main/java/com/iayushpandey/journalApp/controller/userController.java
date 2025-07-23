package com.iayushpandey.journalApp.controller;


import com.iayushpandey.journalApp.Entity.JournalEntry;
import com.iayushpandey.journalApp.Entity.User;
import com.iayushpandey.journalApp.service.JournalEntryService;
import com.iayushpandey.journalApp.service.userService;
import org.apache.coyote.Response;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class userController {
 @Autowired
    private userService userService;

    @GetMapping
    public List<User> getAllUsers(){
        return  userService.getAll();
    }


    @PostMapping
    public void createUser(@RequestBody User user){
         userService.saveEntry(user);
    }

    @PutMapping("/{userName}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable String userName){
        User userInDB = userService.findByUserName(userName);
        if(userInDB != null){
            userInDB.setUserName(userInDB.getUserName());
            userInDB.setPassword(userInDB.getPassword());
//            return new ResponseEntity<>(userInDB, HttpStatus.OK);
        }
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }

}
