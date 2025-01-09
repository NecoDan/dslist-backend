package com.devsuperior.dslist.picpay_challenge.dto.external;

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
