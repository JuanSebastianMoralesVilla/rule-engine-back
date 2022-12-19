package com.perficient.challenge.rulesengine.unit_test;

import com.perficient.challenge.rulesengine.dao.interfaces.TransactionsDao;
import com.perficient.challenge.rulesengine.model.Transaction;
import com.perficient.challenge.rulesengine.processor.RuleProcessor;
import com.perficient.challenge.rulesengine.service.implementation.TransactionServiceImp;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class TransactionServiceTest {

    @Mock
    private TransactionsDao tranDao;

    @Mock
    private RuleProcessor ruleProcessor;

    @InjectMocks
    private TransactionServiceImp transactionService;



    @Test
    public void findAllTest(){
        List<Transaction> transactions = Mockito.mock(List.class);
        Mockito.when(tranDao.findAll()).thenReturn(transactions);
        assertEquals(transactions, transactionService.findAll());
    }

    @Test
    public void findAllNullTest(){
        Mockito.when(tranDao.findAll()).thenReturn(null);
        assertNull(transactionService.findAll());
    }

    @Test
    public void findAllEmptyListTest(){
        List<Transaction> transactions = Mockito.mock(List.class);
        Mockito.when(tranDao.findAll()).thenReturn(transactions);
        assertEquals(0, transactionService.findAll().size());
    }

    @Test
    public void findAllSizedListTest(){
        List<Transaction> transactions = Mockito.mock(List.class);
        Mockito.when(transactions.size()).thenReturn(3);
        Mockito.when(tranDao.findAll()).thenReturn(transactions);
        assertEquals(3, transactionService.findAll().size());
    }

    @Test
    public void getColumnsNullTest(){
        Set<String> columns = Mockito.mock(Set.class);
        Mockito.when(tranDao.getColumns()).thenReturn(null);
        assertNull(transactionService.getColumns());
    }

    @Test
    public void getColumnsTest(){
        Set<String> columns = Mockito.mock(Set.class);
        Mockito.when(tranDao.getColumns()).thenReturn(columns);
        assertEquals(columns, transactionService.getColumns());
    }

    @Test
    public void getColumnsEmptyTest(){
        Set<String> columns = Mockito.mock(Set.class);
        Mockito.when(tranDao.getColumns()).thenReturn(columns);
        assertEquals(0, transactionService.getColumns().size());
    }

    @Test
    public void getColumnsSizedTest(){
        Set<String> columns = Mockito.mock(Set.class);
        Mockito.when(columns.size()).thenReturn(4);
        Mockito.when(tranDao.getColumns()).thenReturn(columns);
        assertEquals(4, transactionService.getColumns().size());
    }
}
