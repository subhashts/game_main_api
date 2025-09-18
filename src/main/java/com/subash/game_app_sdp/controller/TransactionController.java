package com.subash.game_app_sdp.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.subash.game_app_sdp.exceptions.*;
import com.subash.game_app_sdp.modal.Transaction;
import com.subash.game_app_sdp.services.TransactionService;
import com.subash.game_app_sdp.dto.TransactionDto;
import com.subash.game_app_sdp.dto.DtoMapper;

@RestController
@RequestMapping(path = "/transactions")
public class TransactionController {

    @Autowired
    private TransactionService service;

    @PostMapping
    public TransactionDto create(@RequestBody TransactionDto dto) throws BusinessException, IdNotPresentException {
        Transaction created = service.create(DtoMapper.toEntity(dto));
        return DtoMapper.toDto(created);
    }

    @GetMapping
    public List<TransactionDto> findAll() {
        return service.findAll().stream().map(DtoMapper::toDto).collect(Collectors.toList());
    }

    @GetMapping(path = "/{id}")
    public TransactionDto findById(@PathVariable String id) throws TransactionNotFoundException {
        return DtoMapper.toDto(service.findById(id));
    }

    @GetMapping(path = "/member/{memberId}")
    public List<TransactionDto> findByMemberId(@PathVariable String memberId) {
        return service.findByMemberId(memberId).stream().map(DtoMapper::toDto).collect(Collectors.toList());
    }

    @GetMapping(path = "/game/{gameId}")
    public List<TransactionDto> findByGameId(@PathVariable String gameId) {
        return service.findByGameId(gameId).stream().map(DtoMapper::toDto).collect(Collectors.toList());
    }

    @PutMapping(path = "/{id}")
    public TransactionDto update(@PathVariable String id, @RequestBody TransactionDto dto) throws TransactionNotFoundException, BusinessException, IdNotPresentException {
        Transaction updated = service.update(id, DtoMapper.toEntity(dto));
        return DtoMapper.toDto(updated);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable String id) throws TransactionNotFoundException {
        service.delete(id);
    }
}


