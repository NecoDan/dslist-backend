package com.devsuperior.dslist.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReplacementDTO {

    private Integer sourceIndex;
    private Integer destinationIndex;
}
