package com.subash.game_app_sdp.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.subash.game_app_sdp.exceptions.*;
import com.subash.game_app_sdp.services.GameService;
import com.subash.game_app_sdp.modal.Game;
import com.subash.game_app_sdp.repository.GameRepository;
import com.subash.game_app_sdp.dto.GameDto;
import com.subash.game_app_sdp.dto.DtoMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping(path = "/games")
public class GameController {

    private static final Logger logger = LoggerFactory.getLogger(GameController.class);

    @Autowired
    private GameService service;

    // Add home endpoint for root path
    @GetMapping("/")
    public String home() {
        logger.info("Home endpoint accessed");
        return "Game App API is running! Use the following endpoints:\n" +
               "GET /games - Get all games\n" +
               "POST /games - Create a new game\n" +
               "GET /games/{id} - Get game by ID\n" +
               "PUT /games/{id} - Update game\n" +
               "DELETE /games/{id} - Delete game";
    }

    @PostMapping
    public GameDto create(@RequestBody GameDto dto) {
        logger.info("Creating new game: {}", dto.getName());
        return DtoMapper.toDto(service.create(DtoMapper.toEntity(dto)));
    }

    @GetMapping
    public List<GameDto> findAll() {
        logger.info("Fetching all games");
        return service.findAll().stream().map(DtoMapper::toDto).collect(Collectors.toList());
    }

    @GetMapping(path = "/{id}")
    public GameDto findById(@PathVariable String id) throws IdNotPresentException {
        logger.info("Fetching game with ID: {}", id);
        return DtoMapper.toDto(service.findById(id));
    }

    @PutMapping(path = "/{id}")
    public GameDto update(@PathVariable String id, @RequestBody GameDto dto) throws IdNotPresentException {
        logger.info("Updating game with ID: {}", id);
        return DtoMapper.toDto(service.update(id, DtoMapper.toEntity(dto)));
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable String id) throws IdNotPresentException {
        logger.warn("Deleting game with ID: {}", id);
        service.delete(id);
    }
}
