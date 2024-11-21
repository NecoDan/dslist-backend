package com.devsuperior.dslist.game_collections.ports;

import com.devsuperior.dslist.game_collections.dto.GameListDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface GameListPort {
    @Transactional(readOnly = true)
    List<GameListDTO> findAll();

    @Transactional
    void move(Long listId, int sourceIndex, int destinationIndex);

    @Transactional(readOnly = true)
    GameListDTO findById(Long id);
}
