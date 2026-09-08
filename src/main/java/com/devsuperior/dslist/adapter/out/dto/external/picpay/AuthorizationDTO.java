package com.devsuperior.dslist.adapter.out.dto.external.picpay;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorizationDTO implements Serializable {

    @JsonProperty("authorization")
    private boolean authorization;
}
