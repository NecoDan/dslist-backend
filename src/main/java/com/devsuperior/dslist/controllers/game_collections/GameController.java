package com.devsuperior.dslist.controllers.game_collections;

import java.util.Collections;
import java.util.List;

import com.devsuperior.dslist.game_collections.dto.GameMinReportDTO;
import com.devsuperior.dslist.game_collections.ports.GamePort;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.web.bind.annotation.*;

import com.devsuperior.dslist.game_collections.dto.GameDTO;
import com.devsuperior.dslist.game_collections.dto.GameMinDTO;

@RestController
@RequestMapping(value = "/games")
@RequiredArgsConstructor
public class GameController {

    private final GamePort gamePort;

    @GetMapping(value = "/{id}")
    @Caching(
            cacheable = {
                    @Cacheable(
                            cacheNames = "games",
                            cacheManager = "cacheManager2Minutes",
                            key = "#id"
                    )
            },
            evict = {
                    @CacheEvict(
                            cacheNames = "gamesList",
                            allEntries = true
                    ),
                    @CacheEvict(
                            cacheNames = "gamesMin",
                            allEntries = true
                    ),
            }
    )
    public GameDTO findById(@PathVariable Long id) {
        return gamePort.findById(id);
    }

    @GetMapping
    public List<GameMinDTO> findAll() {
        return gamePort.findAll();
    }


    @GetMapping(value = "/search")
    public List<GameMinDTO> findAllByYearRelease(@RequestParam(value = "year", defaultValue = "0") Integer year) {
        return gamePort.findAllByYearRelease(year);
    }

    public List<GameMinDTO> findAllByScore(@RequestParam(value = "min") Double min, @RequestParam(value = "max") Double max) {
        return Collections.emptyList();
    }

    @GetMapping(value = "/search/ranking/top/{value}")
    public List<GameMinReportDTO> findAllRankingTopBy(@PathVariable Integer value) {
        return gamePort.findAllRankingTopBy(value);
    }
}
