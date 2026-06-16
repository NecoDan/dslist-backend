package com.devsuperior.dslist.itau_v1_challenge.service;

import com.devsuperior.dslist.itau_v1_challenge.biz.TransactionItauBusiness;
import com.devsuperior.dslist.itau_v1_challenge.dto.internal.StatisticsItauResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatiticsTransactionItauService {

    private final TransactionItauBusiness transactionItauBusiness;

    public StatisticsItauResponseDTO calculateStatistics(Integer secondsRange) {
        log.info("Calculating statistics for Itau transactions");

        var dateTimeRange = OffsetDateTime.now().minusSeconds(secondsRange);

        final var doubleSummaryStatistics = transactionItauBusiness.getTransactionsByDateTimeInMemory(dateTimeRange)
                .stream()
                .mapToDouble(value -> value.getAmount().doubleValue())
                .summaryStatistics();

        return new StatisticsItauResponseDTO(
                doubleSummaryStatistics.getCount(),
                doubleSummaryStatistics.getSum(),
                doubleSummaryStatistics.getAverage(),
                doubleSummaryStatistics.getMin(),
                doubleSummaryStatistics.getAverage()
        );
    }
}
