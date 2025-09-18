package com.subash.game_app_sdp.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.subash.game_app_sdp.exceptions.BusinessException;
import com.subash.game_app_sdp.exceptions.CollectionNotFoundException;
import com.subash.game_app_sdp.modal.CollectionEntry;
import com.subash.game_app_sdp.repository.CollectionEntryRepository;

@Service
public class CollectionEntryService {

    @Autowired
    private CollectionEntryRepository repo;

    public CollectionEntry create(CollectionEntry entry) throws BusinessException {
        entry.setId(null);
        if (entry.getAmount() == null) {
            throw new BusinessException("amount is required");
        }
        if (entry.getDate() == null) {
            entry.setDate(new Date());
        }
        return repo.save(entry);
    }

    public List<CollectionEntry> findAll() {
        return repo.findAll();
    }

    public CollectionEntry findById(String id) throws CollectionNotFoundException {
        Optional<CollectionEntry> optional = repo.findById(id);
        if (optional.isEmpty()) {
            throw new CollectionNotFoundException("Collection not found: " + id);
        }
        return optional.get();
    }

    public CollectionEntry update(String id, CollectionEntry input) throws CollectionNotFoundException, BusinessException {
        Optional<CollectionEntry> optional = repo.findById(id);
        if (optional.isEmpty()) {
            throw new CollectionNotFoundException("Collection not found: " + id);
        }
        CollectionEntry existing = optional.get();
        if (input.getAmount() != null) {
            existing.setAmount(input.getAmount());
        }
        if (input.getDate() != null) {
            existing.setDate(input.getDate());
        }
        return repo.save(existing);
    }

    public boolean delete(String id) throws CollectionNotFoundException {
        Optional<CollectionEntry> optional = repo.findById(id);
        if (optional.isEmpty()) {
            throw new CollectionNotFoundException("Collection not found: " + id);
        }
        repo.deleteById(id);
        return true;
    }
}


