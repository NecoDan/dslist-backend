//package com.devsuperior.dslist.picpay_challenge.service;
//
//import com.devsuperior.dslist.core.domain.picpay_challenge.Transaction;
//import com.devsuperior.dslist.core.domain.picpay_challenge.User;
//import com.devsuperior.dslist.core.ports.picpay_challenge.AuthorizationPicPayPort;
//import com.devsuperior.dslist.core.ports.picpay_challenge.NotificationPicPayPort;
//import com.devsuperior.dslist.core.ports.picpay_challenge.TransactionPicPayPort;
//import com.devsuperior.dslist.core.ports.picpay_challenge.UserPicPayPort;
//import com.devsuperior.dslist.core.usecase.picpay_challenge.BalanceManagerPicPayUserCase;
//import com.devsuperior.dslist.core.usecase.picpay_challenge.TransactionPicPayCreateUseCaseImpl;
//import com.devsuperior.dslist.core.usecase.picpay_challenge.input.TransactionRequestDTO;
//import com.devsuperior.dslist.exceptions.EntityCreateFailedException;
//import org.apache.commons.lang3.RandomUtils;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.doNothing;
//
//class TransactionPicPayCreateUseCaseImplTest {
//
//    @Mock
//    TransactionPicPayPort transactionPicPayPort;
//
//    Mock
//    UserPicPayPort userPicPayPort;
//
//    Mock
//    BalanceManagerPicPayUserCase balanceManagerPicPayUserCase;
//
//    Mock
//    NotificationPicPayPort notificationPicPayPort;
//
//    Mock
//    AuthorizationPicPayPort authorizationPicPayPort;
//
//    @InjectMocks
//    private TransactionPicPayCreateUseCaseImpl transactionPicPayCreateUseCase;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    private Transaction getBuildTransaction() {
//        return Transaction.builder()
//                .id(RandomUtils.secure().randomLong())
//                .createdAt(LocalDateTime.now())
//                .receiver(User.builder()
//                        .id(RandomUtils.secure().randomLong())
//                        .balance(BigDecimal.valueOf(RandomUtils.secure().randomDouble()))
//                        .build())
//                .sender(User.builder()
//                        .id(RandomUtils.secure().randomLong())
//                        .balance(BigDecimal.valueOf(RandomUtils.secure().randomDouble()))
//                        .build())
//                .amount(BigDecimal.valueOf(RandomUtils.secure().randomDouble()))
//                .build();
//    }
//
//    @Test
//    void createTransactionSuccessfully() {
//        // -- 01_Cenário
//        final var amountValue = BigDecimal.valueOf(100);
//        var transaction = getBuildTransaction();
//        transaction.setAmount(amountValue);
//
//        final var requestDTO = new TransactionRequestDTO(amountValue,
//                transaction.getSender().getId(),
//                transaction.getReceiver().getId());
//
//        doNothing()
//                .when(transactionPicPayPort).createTransaction((any(Transaction.class)));
//
//        // -- 02_Ação
//        final var response = transactionPicPayCreateUseCase.createTransaction(requestDTO);
//
//        // -- 03_Verificação_Validação
//        assertNotNull(response);
//        assertNotNull(response.getId());
//        assertEquals(transaction.getId(), response.getId());
//        assertEquals(transaction.getSender().getId(), response.getSender().getId());
//        assertEquals(transaction.getReceiver().getId(), response.getReceiver().getId());
//    }
//
//    @Test
//    void createTransactionThrowsExceptionWhenPortFails() {
//        // -- 01_Cenário
//        final var requestDTO = new TransactionRequestDTO(
//                BigDecimal.valueOf(100),
//                1L,
//                2L
//        );
//
//        // -- 02_Ação & 03_Verificação_Validação
//        final var exception = assertThrows(EntityCreateFailedException.class,
//                () -> transactionPicPayCreateUseCase.createTransaction(requestDTO)
//        );
//
//        assertEquals("Transaction creation failed", exception.getMessage());
//    }
//
//    //    @Test
//    void createTransactionHandlesNullRequest() {
//        // -- 01_Cenário & 02_Ação & 03_Verificação_Validação
//        assertThrows(EntityCreateFailedException.class,
//                () -> transactionPicPayCreateUseCase.createTransaction(null)
//        );
//    }
//}