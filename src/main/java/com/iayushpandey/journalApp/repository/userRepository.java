package com.iayushpandey.journalApp.repository;

//Controller ---> Service ----> Repository

import com.iayushpandey.journalApp.Entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface userRepository extends MongoRepository<User, ObjectId> {

User findByUserName (String userName);


}
