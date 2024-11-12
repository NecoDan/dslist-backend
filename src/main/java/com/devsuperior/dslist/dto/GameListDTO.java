package com.devsuperior.dslist.dto;

import com.devsuperior.dslist.entities.GameList;
import com.devsuperior.dslist.utils.FunctionalUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameListDTO {


    private Long id;
    private String name;
    private String creationDate;

    public GameListDTO(final GameList entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.creationDate = FunctionalUtils.formatCreationDate(entity.getCreatedAt());
    }
}
