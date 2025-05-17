package com.alakey.pioneerpicsel;

import com.alakey.pioneerpicsel.entity.Account;
import com.alakey.pioneerpicsel.repository.AccountRepository;
import com.alakey.pioneerpicsel.service.impl.TransferServiceImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransferServiceImplTest {

    @InjectMocks
    private TransferServiceImpl transferService;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private EntityManager entityManager;

    @Mock
    private TypedQuery<Account> queryMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSuccessfulTransfer() {
        Account from = new Account(1L, null, new BigDecimal("100.00"), new BigDecimal("100.00"));
        Account to = new Account(2L, null, new BigDecimal("50.00"), new BigDecimal("50.00"));

        TypedQuery<Account> fromQuery = mock(TypedQuery.class);
        TypedQuery<Account> toQuery = mock(TypedQuery.class);

        when(entityManager.createQuery(anyString(), eq(Account.class)))
                .thenReturn(fromQuery)
                .thenReturn(toQuery);

        when(fromQuery.setParameter(eq("uid"), eq(1L))).thenReturn(fromQuery);
        when(fromQuery.setLockMode(any())).thenReturn(fromQuery);
        when(fromQuery.getSingleResult()).thenReturn(from);

        when(toQuery.setParameter(eq("uid"), eq(2L))).thenReturn(toQuery);
        when(toQuery.setLockMode(any())).thenReturn(toQuery);
        when(toQuery.getSingleResult()).thenReturn(to);

        transferService.transfer(1L, 2L, new BigDecimal("30.00"));

        assertEquals(new BigDecimal("70.00"), from.getBalance());
        assertEquals(new BigDecimal("80.00"), to.getBalance());

        verify(accountRepository).save(from);
        verify(accountRepository).save(to);
    }

    @Test
    void testTransferInsufficientBalance() {
        Account from = new Account(1L, null, new BigDecimal("10.00"), new BigDecimal("10.00"));
        Account to = new Account(2L, null, new BigDecimal("50.00"), new BigDecimal("50.00"));

        when(entityManager.createQuery(anyString(), eq(Account.class))).thenReturn(queryMock);
        when(queryMock.setParameter(eq("uid"), anyLong())).thenReturn(queryMock);
        when(queryMock.setLockMode(any())).thenReturn(queryMock);
        when(queryMock.getSingleResult()).thenReturn(from).thenReturn(to);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
            transferService.transfer(1L, 2L, new BigDecimal("30.00"))
        );

        assertEquals("Insufficient balance", ex.getMessage());
    }

    @Test
    void testTransferToSelf() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
            transferService.transfer(1L, 1L, new BigDecimal("10.00"))
        );

        assertEquals("Cannot transfer to self.", ex.getMessage());
    }
}
