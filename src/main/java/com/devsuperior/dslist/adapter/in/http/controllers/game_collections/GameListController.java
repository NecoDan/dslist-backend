package com.devsuperior.dslist.adapter.in.http.controllers.game_collections;

import com.devsuperior.dslist.game_collections.dto.GameListDTO;
import com.devsuperior.dslist.game_collections.dto.GameMinDTO;
import com.devsuperior.dslist.game_collections.dto.ReplacementDTO;
import com.devsuperior.dslist.game_collections.ports.GameListPort;
import com.devsuperior.dslist.game_collections.ports.GamePort;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/lists")
@RequiredArgsConstructor
@Hidden
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
