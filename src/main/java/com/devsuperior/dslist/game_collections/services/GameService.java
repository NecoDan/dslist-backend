package com.devsuperior.dslist.game_collections.services;

import com.devsuperior.dslist.game_collections.dto.GameDTO;
import com.devsuperior.dslist.game_collections.dto.GameMinDTO;
import com.devsuperior.dslist.game_collections.dto.GameMinReportDTO;
import com.devsuperior.dslist.game_collections.ports.GamePort;
import com.devsuperior.dslist.game_collections.repositories.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

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
        return gameRepository.searchByList(listId)
                .stream()
                .map(GameMinDTO::new)
                .toList();
    }

    @Override
    public List<GameMinDTO> findAllByYearRelease(final Integer year) {
        return gameRepository.recoveryByYear(year)
                .stream()
                .map(GameMinDTO::new)
                .toList();
    }

    @Override
    public List<GameMinReportDTO> findAllRankingTopBy(Integer valueTop) {
        return gameRepository.recoverTopRankingByScoreOrder(valueTop).stream()
                .map(GameMinReportDTO::new)
                .toList();
    }
}
