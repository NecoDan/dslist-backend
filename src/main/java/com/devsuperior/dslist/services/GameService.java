package com.devsuperior.dslist.services;

import java.util.Collections;
import java.util.List;

import com.devsuperior.dslist.ports.GamePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import com.devsuperior.dslist.dto.GameDTO;
import com.devsuperior.dslist.dto.GameMinDTO;
import com.devsuperior.dslist.repositories.GameRepository;

@Service
@RequiredArgsConstructor
public class GameService implements GamePort {

    private final GameRepository gameRepository;

    @Transactional(readOnly = true)
    @Override
    public GameDTO findById(@PathVariable Long listId) {
        return new GameDTO(gameRepository.findById(listId)
                .orElseThrow(() -> new RuntimeException("Game not found")));
    }

    @Transactional(readOnly = true)
    @Override
    public List<GameMinDTO> findAll() {
        return gameRepository.findAll()
                .stream()
                .map(GameMinDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<GameMinDTO> findByGameList(Long listId) {
        return gameRepository.searchByList(listId).stream().map(GameMinDTO::new).toList();
    }

    @Override
    public List<GameMinDTO> findAllByYearRelease(final Integer year) {
        return gameRepository.recoveryByYear(year)
                .stream()
                .map(GameMinDTO::new)
                .toList();
    }
}
