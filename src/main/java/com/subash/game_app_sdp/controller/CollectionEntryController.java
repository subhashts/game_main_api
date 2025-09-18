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
import com.subash.game_app_sdp.modal.CollectionEntry;
import com.subash.game_app_sdp.services.CollectionEntryService;
import com.subash.game_app_sdp.dto.CollectionEntryDto;
import com.subash.game_app_sdp.dto.DtoMapper;

@RestController
@RequestMapping(path = "/collections")
public class CollectionEntryController {

    @Autowired
    private CollectionEntryService service;

    @PostMapping
    public CollectionEntryDto create(@RequestBody CollectionEntryDto dto) throws BusinessException {
        return DtoMapper.toDto(service.create(DtoMapper.toEntity(dto)));
    }

    @GetMapping
    public List<CollectionEntryDto> findAll() {
        return service.findAll().stream().map(DtoMapper::toDto).collect(Collectors.toList());
    }

    @GetMapping(path = "/{id}")
    public CollectionEntryDto findById(@PathVariable String id) throws CollectionNotFoundException {
        return DtoMapper.toDto(service.findById(id));
    }

    @PutMapping(path = "/{id}")
    public CollectionEntryDto update(@PathVariable String id, @RequestBody CollectionEntryDto dto) throws CollectionNotFoundException, BusinessException {
        return DtoMapper.toDto(service.update(id, DtoMapper.toEntity(dto)));
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable String id) throws CollectionNotFoundException {
        service.delete(id);
    }
}


