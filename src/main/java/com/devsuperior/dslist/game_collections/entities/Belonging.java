package com.devsuperior.dslist.game_collections.entities;

import java.io.Serializable;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_belonging", schema = "dslistapp")
@Getter
@Setter
@EqualsAndHashCode
public class Belonging implements Serializable {

    @EmbeddedId
    private BelongingPK id = new BelongingPK();

    private Integer position;

    public void setGame(Game game) {
        id.setGame(game);
    }

    public Game getGame() {
        return id.getGame();
    }

    public void setList(GameList list) {
        id.setList(list);
    }

    public GameList getList() {
        return id.getList();
    }
}
