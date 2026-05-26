package com.devsuperior.dslist.game_collections.entities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class BelongingPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "game_id")
    @Transient
    private Game game;

    @ManyToOne
    @JoinColumn(name = "list_id")
    @Transient
    private GameList list;
}
