package com.subash.game_app_sdp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.subash.game_app_sdp.exceptions.BusinessException;
import com.subash.game_app_sdp.exceptions.AdminUserNotFoundException;
import com.subash.game_app_sdp.modal.AdminUser;
import com.subash.game_app_sdp.repository.AdminUserRepository;

@Service
public class AdminUserService {

    @Autowired
    private AdminUserRepository repo;

    public AdminUser create(AdminUser user) throws BusinessException {
        user.setId(null);
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            throw new BusinessException("username is required");
        }
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            throw new BusinessException("password is required");
        }
        if (repo.existsByUsername(user.getUsername())) {
            throw new BusinessException("username already exists: " + user.getUsername());
        }
        return repo.save(user);
    }

    public List<AdminUser> findAll() {
        return repo.findAll();
    }

    public AdminUser findById(String id) throws AdminUserNotFoundException {
        Optional<AdminUser> optional = repo.findById(id);
        if (optional.isEmpty()) {
            throw new AdminUserNotFoundException("Admin user not found: " + id);
        }
        return optional.get();
    }

    public AdminUser update(String id, AdminUser input) throws AdminUserNotFoundException, BusinessException {
        Optional<AdminUser> optional = repo.findById(id);
        if (optional.isEmpty()) {
            throw new AdminUserNotFoundException("Admin user not found: " + id);
        }
        AdminUser existing = optional.get();
        if (input.getUsername() != null && !input.getUsername().trim().isEmpty()) {
            if (!input.getUsername().equals(existing.getUsername()) && repo.existsByUsername(input.getUsername())) {
                throw new BusinessException("username already exists: " + input.getUsername());
            }
            existing.setUsername(input.getUsername());
        }
        if (input.getPassword() != null && !input.getPassword().trim().isEmpty()) {
            existing.setPassword(input.getPassword());
        }
        return repo.save(existing);
    }

    public boolean delete(String id) throws AdminUserNotFoundException {
        Optional<AdminUser> optional = repo.findById(id);
        if (optional.isEmpty()) {
            throw new AdminUserNotFoundException("Admin user not found: " + id);
        }
        repo.deleteById(id);
        return true;
    }
}


