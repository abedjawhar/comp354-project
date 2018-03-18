package com.github.comp354project.model.account;


import com.github.comp354project.BusinessRulesConstants;
import com.github.comp354project.model.TestUtils;
import com.github.comp354project.model.exceptions.ValidationException;
import com.github.comp354project.model.user.User;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.table.TableUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.SQLException;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceTest{

    private TransactionService transactionService;
    private Dao<Transaction, Integer> transactionDao;
    private JdbcConnectionSource connectionSource;

    @Before
    public void setUp() throws Exception{
        connectionSource = new JdbcConnectionSource("jdbc:sqlite::memory:");
        transactionDao = DaoManager.createDao(connectionSource, Transaction.class);
        transactionService = new TransactionService(transactionDao);
        TableUtils.createTable(connectionSource, User.class);
        TableUtils.createTable(connectionSource, Account.class);
        TableUtils.createTable(connectionSource, Transaction.class);
    }

    @Test(expected = ValidationException.class)
    public void testUpdateCategory_withNullTransactionID_shouldThrow() throws ValidationException{
        transactionService.updateTransactionCategory(null, "Leisure");
    }

    @Test(expected = ValidationException.class)
    public void testUpdateCategory_withNonexistentTransaction_shouldThrow() throws ValidationException{
        transactionService.updateTransactionCategory(111111, "Leisure");
    }

    @Test
    public void testUpdateCategory_withNullCategory_shouldSucceed() throws Exception{
       updateCategory(TestUtils.testTransaction, null);
    }

    @Test
    public void testUpdateCategory_withEmptyCategory_shouldSucceed() throws Exception{
        updateCategory(TestUtils.testTransaction, "");
    }

    @Test
    public void setUpdateCategory_withValidCategory_shouldSucceed() throws Exception{
        updateCategory(TestUtils.testTransaction, "Leisure");
    }

    @Test(expected = ValidationException.class)
    public void testUpdateCategory_withInvalidCategory_shouldThrow() throws Exception{
        String category = String.join("", Collections.nCopies(BusinessRulesConstants.CATEGORY_MAX_LENGTH + 1, "A"));
        assertTrue(category.length() > BusinessRulesConstants.CATEGORY_MAX_LENGTH);

        updateCategory(TestUtils.testTransaction, category);
    }

    private void updateCategory(Transaction transaction, String category) throws Exception{
        transactionDao.create(transaction);

        Transaction updatedTransaction = transactionService.updateTransactionCategory(transaction.getID(), category);

        assertEquals(category, updatedTransaction.getCategory());
    }
}