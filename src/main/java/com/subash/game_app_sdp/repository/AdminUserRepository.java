package com.subash.game_app_sdp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.subash.game_app_sdp.modal.AdminUser;

@Repository
public interface AdminUserRepository extends MongoRepository<AdminUser, String> {
    boolean existsByUsername(String username);
}


