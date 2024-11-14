package com.devsuperior.dslist.controllers;

import java.util.Collections;
import java.util.List;

import com.devsuperior.dslist.dto.GameMinReportDTO;
import com.devsuperior.dslist.ports.GamePort;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
