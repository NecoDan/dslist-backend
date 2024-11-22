package com.devsuperior.dslist.game_collections.ports;

import com.devsuperior.dslist.game_collections.dto.GameDTO;
import com.devsuperior.dslist.game_collections.dto.GameMinDTO;
import com.devsuperior.dslist.game_collections.dto.GameMinReportDTO;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface GamePort {
    @Transactional(readOnly = true)
    GameDTO findById(@PathVariable Long listId);

    @Transactional(readOnly = true)
    List<GameMinDTO> findAll();

    @Transactional(readOnly = true)
    List<GameMinDTO> findByGameList(Long listId);

    List<GameMinDTO> findAllByYearRelease(final Integer year);

    List<GameMinReportDTO> findAllRankingTopBy(final Integer valueTop);
}
