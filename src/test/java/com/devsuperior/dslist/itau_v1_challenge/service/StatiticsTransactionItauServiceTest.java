package com.devsuperior.dslist.itau_v1_challenge.service;

import com.devsuperior.dslist.itau_v1_challenge.ports.TransactionItauPort;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class StatiticsTransactionItauServiceTest {

    @Mock
    private TransactionItauPort transactionItauPort;

    @InjectMocks
    private StatiticsTransactionItauService statiticsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

}