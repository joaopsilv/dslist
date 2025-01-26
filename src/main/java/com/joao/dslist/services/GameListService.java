package com.joao.dslist.services;

import com.joao.dslist.dto.GameListDTO;
import com.joao.dslist.entities.GameList;
import com.joao.dslist.projections.GameMinProjection;
import com.joao.dslist.repositories.GameListRepository;
import com.joao.dslist.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GameListService {
    @Autowired
    private GameListRepository gameListRepository;

    @Autowired
    private GameRepository gameRepository;

    @Transactional(readOnly = true)
    public List<GameListDTO> findAll() {
        List<GameList> result = gameListRepository.findAll();
        return result.stream().map(r -> new GameListDTO(r)).toList();
    }

    @Transactional(readOnly = true)
    public void move(Long listId, int sourceIndex, int destinationIndex) {
        List<GameMinProjection> games = gameRepository.searchByList(listId);
        GameMinProjection game = games.remove(sourceIndex);
        games.add(destinationIndex, game);

        int min = Math.min(sourceIndex, destinationIndex);
        int max = Math.max(sourceIndex, destinationIndex);

        for (int i = min; i <= max; i++) {
            gameListRepository.updateBelongingPosition(listId, games.get(i).getId(), i);
        };
    }
}
