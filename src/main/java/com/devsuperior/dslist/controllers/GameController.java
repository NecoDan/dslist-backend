package com.devsuperior.dslist.controllers;

import java.util.List;

import com.devsuperior.dslist.ports.GamePort;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dslist.dto.GameDTO;
import com.devsuperior.dslist.dto.GameMinDTO;

@RestController
@RequestMapping(value = "/games")
@RequiredArgsConstructor
public class GameController {

    private final GamePort gamePort;

    @GetMapping(value = "/{id}")
    public GameDTO findById(@PathVariable Long id) {
        return gamePort.findById(id);
    }

    @GetMapping
    public List<GameMinDTO> findAll() {
        return gamePort.findAll();
    }
}
