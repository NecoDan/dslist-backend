package com.devsuperior.dslist.ports;

import com.devsuperior.dslist.dto.GameDTO;
import com.devsuperior.dslist.dto.GameMinDTO;
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
}
