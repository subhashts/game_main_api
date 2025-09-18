package com.subash.game_app_sdp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.subash.game_app_sdp.modal.CollectionEntry;

@Repository
public interface CollectionEntryRepository extends MongoRepository<CollectionEntry, String> {
}


