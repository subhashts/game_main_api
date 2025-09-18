package com.subash.game_app_sdp.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.subash.game_app_sdp.exceptions.BusinessException;
import com.subash.game_app_sdp.exceptions.IdNotPresentException;
import com.subash.game_app_sdp.exceptions.TransactionNotFoundException;
import com.subash.game_app_sdp.modal.Transaction;
import com.subash.game_app_sdp.repository.TransactionRepository;
import com.subash.game_app_sdp.repository.MemberRepository;
import com.subash.game_app_sdp.repository.GameRepository;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository repo;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private GameRepository gameRepository;

    public Transaction create(Transaction tx) throws BusinessException, IdNotPresentException {
        tx.setId(null);
        if (tx.getAmount() == null) {
            throw new BusinessException("amount is required");
        }
        if (tx.getMemberId() == null || tx.getMemberId().trim().isEmpty()) {
            throw new BusinessException("memberId is required");
        }
        if (tx.getGameId() == null || tx.getGameId().trim().isEmpty()) {
            throw new BusinessException("gameId is required");
        }
        if (!memberRepository.existsById(tx.getMemberId())) {
            throw new IdNotPresentException("Member not found: " + tx.getMemberId());
        }
        if (!gameRepository.existsById(tx.getGameId())) {
            throw new IdNotPresentException("Game not found: " + tx.getGameId());
        }
        if (tx.getDateTime() == null) {
            tx.setDateTime(new Date());
        }
        return repo.save(tx);
    }

    public List<Transaction> findAll() {
        return repo.findAll();
    }

    public Transaction findById(String id) throws TransactionNotFoundException {
        Optional<Transaction> optional = repo.findById(id);
        if (optional.isEmpty()) {
            throw new TransactionNotFoundException("Transaction not found: " + id);
        }
        return optional.get();
    }

    public List<Transaction> findByMemberId(String memberId) {
        return repo.findByMemberId(memberId);
    }

    public List<Transaction> findByGameId(String gameId) {
        return repo.findByGameId(gameId);
    }

    public Transaction update(String id, Transaction input) throws TransactionNotFoundException, BusinessException, IdNotPresentException {
        Optional<Transaction> optional = repo.findById(id);
        if (optional.isEmpty()) {
            throw new TransactionNotFoundException("Transaction not found: " + id);
        }
        Transaction existing = optional.get();
        if (input.getAmount() != null) {
            existing.setAmount(input.getAmount());
        }
        if (input.getMemberId() != null && !input.getMemberId().trim().isEmpty()) {
            if (!memberRepository.existsById(input.getMemberId())) {
                throw new IdNotPresentException("Member not found: " + input.getMemberId());
            }
            existing.setMemberId(input.getMemberId());
        }
        if (input.getGameId() != null && !input.getGameId().trim().isEmpty()) {
            if (!gameRepository.existsById(input.getGameId())) {
                throw new IdNotPresentException("Game not found: " + input.getGameId());
            }
            existing.setGameId(input.getGameId());
        }
        if (input.getDateTime() != null) {
            existing.setDateTime(input.getDateTime());
        }
        return repo.save(existing);
    }

    public boolean delete(String id) throws TransactionNotFoundException {
        Optional<Transaction> optional = repo.findById(id);
        if (optional.isEmpty()) {
            throw new TransactionNotFoundException("Transaction not found: " + id);
        }
        repo.deleteById(id);
        return true;
    }
}


