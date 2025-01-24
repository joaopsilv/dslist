package com.joao.dslist.services;

import com.joao.dslist.dto.GameDTO;
import com.joao.dslist.dto.GameMinDTO;
import com.joao.dslist.entities.Game;
import com.joao.dslist.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;

    @Transactional(readOnly = true)
    public List<GameMinDTO> findAll() {
        List<Game> games = gameRepository.findAll();
        return games.stream().map(game -> new GameMinDTO(game)).toList();
    }

    @Transactional(readOnly = true)
    public GameDTO findById(Long id) {
        Game game = gameRepository.findById(id).get();
        return new GameDTO(game);
    }
}
