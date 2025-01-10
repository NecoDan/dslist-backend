package com.devsuperior.dslist.game_collections.services;

import java.util.List;

import com.devsuperior.dslist.game_collections.ports.GameListPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dslist.game_collections.dto.GameListDTO;
import com.devsuperior.dslist.game_collections.projections.GameMinProjection;
import com.devsuperior.dslist.game_collections.repository.GameListRepository;
import com.devsuperior.dslist.game_collections.repository.GameRepository;

@Service
@RequiredArgsConstructor
public class GameListService implements GameListPort {

    private final GameListRepository gameListRepository;

    private final GameRepository gameRepository;

    @Transactional(readOnly = true)
    @Override
    public List<GameListDTO> findAll() {
        return gameListRepository.findAll()
                .stream()
                .map(GameListDTO::new)
                .toList();
    }

    @Transactional
    @Override
    public void move(Long listId, int sourceIndex, int destinationIndex) {

        List<GameMinProjection> list = gameRepository.searchByList(listId);

        GameMinProjection obj = list.remove(sourceIndex);
        list.add(destinationIndex, obj);

        // int min = sourceIndex < destinationIndex ? sourceIndex : destinationIndex;
        // int max = sourceIndex < destinationIndex ? destinationIndex : sourceIndex;
        int min = Math.min(sourceIndex, destinationIndex);
        int max = Math.max(sourceIndex, destinationIndex);

        for (int i = min; i <= max; i++) {
            gameListRepository.updateBelongingPosition(listId, list.get(i).getId(), i);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public GameListDTO findById(Long id) {
        return new GameListDTO(
                gameListRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("GameList not found"))
        );
    }
}

