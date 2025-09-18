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
import com.subash.game_app_sdp.modal.Member;
import com.subash.game_app_sdp.services.MemberService;
import com.subash.game_app_sdp.exceptions.*;
import com.subash.game_app_sdp.dto.MemberDto;
import com.subash.game_app_sdp.dto.DtoMapper;

@RestController
@RequestMapping(path = "/members")
public class MemberController {
    
    @Autowired
    private MemberService service;
    
    // Add home endpoint for root path
    @GetMapping("/")
    public String home() {
        return "Member API is running! Use the following endpoints:\n" +
               "GET /members - Get all members\n" +
               "POST /members - Create a new member\n" +
               "GET /members/{id} - Get member by ID\n" +
               "PUT /members/{id} - Update member\n" +
               "DELETE /members/{id} - Delete member";
    }
    
    @PostMapping
    public MemberDto create(@RequestBody MemberDto dto) throws BusinessException {
        return DtoMapper.toDto(service.create(DtoMapper.toEntity(dto)));
    }
    
    @GetMapping
    public List<MemberDto> findAll() {
        return service.findAll().stream().map(DtoMapper::toDto).collect(Collectors.toList());
    }
    
    @GetMapping(path = "/{id}")
    public MemberDto findById(@PathVariable String id) throws IdNotPresentException {
        return DtoMapper.toDto(service.findById(id));
    }
    
    @PutMapping(path = "/{id}")
    public MemberDto update(@PathVariable String id, @RequestBody MemberDto dto) throws IdNotPresentException, BusinessException {
        return DtoMapper.toDto(service.update(id, DtoMapper.toEntity(dto)));
    }
    
    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable String id) throws IdNotPresentException {
        service.delete(id);
    }
}
