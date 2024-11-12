package com.devsuperior.dslist.dto;

import com.devsuperior.dslist.entities.GameList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameListDTO {

    private final static String BR_DATETIME_FORMAT = "dd/MM/yyyy HH:mm:ss";

    private Long id;
    private String name;
    private String creationDate;

    public GameListDTO(final GameList entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.creationDate = formatCreationDate(entity.getCreatedAt());
    }

    private String formatCreationDate(LocalDateTime localDateTime) {
        return (Objects.isNull(localDateTime)) ? StringUtils.EMPTY : formatCreationDateBy(localDateTime);
    }

    private String formatCreationDateBy(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.ofPattern(BR_DATETIME_FORMAT));
    }
}
