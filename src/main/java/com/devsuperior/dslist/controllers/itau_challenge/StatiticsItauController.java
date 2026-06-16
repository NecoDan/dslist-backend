package com.devsuperior.dslist.controllers.itau_challenge;

import com.devsuperior.dslist.itau_v1_challenge.dto.internal.StatisticsItauResponseDTO;
import com.devsuperior.dslist.itau_v1_challenge.service.StatiticsTransactionItauService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/itau/statistics")
public class StatiticsItauController {

    private final StatiticsTransactionItauService statiticsService;

    @GetMapping(value = "/v1/")
    public ResponseEntity<StatisticsItauResponseDTO> getStatisticsSummary
            (@RequestParam(
                    value = "intervaloBusca",
                    required = false,
                    defaultValue = "60"
            ) Integer intervaloBusca) {

        return ResponseEntity.ok(statiticsService.calculateStatistics(intervaloBusca));
    }
}
