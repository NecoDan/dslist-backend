package com.devsuperior.dslist.adapter.out.dto.external.picpay;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataAuthorizationDTO {

    @JsonProperty("status")
    private String status;

    @JsonProperty("data")
    private AuthorizationDTO data;
}
