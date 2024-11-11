package com.devsuperior.dslist.dto;

import com.devsuperior.dslist.entities.GameList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameListDTO {

    private Long id;
    private String name;
    private LocalDateTime creationDate;

    public GameListDTO(final GameList entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.creationDate = entity.getCreatedAt();
    }
}
