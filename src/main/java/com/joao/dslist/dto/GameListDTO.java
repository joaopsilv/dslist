package com.joao.dslist.dto;

import com.joao.dslist.entities.GameList;

public class GameListDTO {
    private long id;
    private String name;

    public GameListDTO() {}

    public GameListDTO(GameList gameList) {
        id = gameList.getId();
        name = gameList.getName();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
