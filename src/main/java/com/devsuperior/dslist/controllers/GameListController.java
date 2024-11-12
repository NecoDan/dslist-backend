package com.devsuperior.dslist.controllers;

import java.util.List;

import com.devsuperior.dslist.ports.GameListPort;
import com.devsuperior.dslist.ports.GamePort;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dslist.dto.GameListDTO;
import com.devsuperior.dslist.dto.GameMinDTO;
import com.devsuperior.dslist.dto.ReplacementDTO;

@RestController
@RequestMapping(value = "/lists")
@RequiredArgsConstructor
public class GameListController {

    private final GameListPort gameListPort;

    private final GamePort gamePort;

    @GetMapping(value = "/{id}")
    public GameListDTO findById(@PathVariable Long id) {
        return gameListPort.findById(id);
    }

    @GetMapping
    public List<GameListDTO> findAll() {
        return gameListPort.findAll();
    }

    @GetMapping(value = "/{listId}/games")
    public List<GameMinDTO> findGames(@PathVariable Long listId) {
        return gamePort.findByGameList(listId);
    }

    @PostMapping(value = "/{listId}/replacement")
    public void move(@PathVariable Long listId, @RequestBody ReplacementDTO body) {
        gameListPort.move(listId, body.getSourceIndex(), body.getDestinationIndex());
    }
}
