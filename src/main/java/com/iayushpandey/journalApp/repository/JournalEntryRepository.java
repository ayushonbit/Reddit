package com.iayushpandey.journalApp.repository;

//Controller ---> Service ----> Repository

import com.iayushpandey.journalApp.Entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalEntryRepository extends MongoRepository<JournalEntry, ObjectId> {




}
