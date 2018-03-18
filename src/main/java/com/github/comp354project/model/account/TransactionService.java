package com.github.comp354project.model.account;

import com.github.comp354project.model.exceptions.DatabaseException;
import com.github.comp354project.model.exceptions.ValidationError;
import com.github.comp354project.model.exceptions.ValidationException;
import com.github.comp354project.model.validators.ICategoryNameValidator;
import com.github.comp354project.model.validators.ValidatorFactory;
import com.j256.ormlite.dao.Dao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionService implements ITransactionService {
    private static final Logger logger = LogManager.getLogger(TransactionService.class);

    private Dao<Transaction, Integer> transactionDao;
    private ICategoryNameValidator categoryValidator;

    @Inject
    public TransactionService(Dao<Transaction, Integer> transactionDao) {
        this.transactionDao = transactionDao;
        this.categoryValidator = ValidatorFactory.categoryNameValidator();
    }

    @Override
    public Transaction updateTransactionCategory(Integer transactionID, String category) throws ValidationException {
        List<ValidationError> errors = new ArrayList<>();
        if(transactionID == null){
            errors.add(ValidationError.builder()
                        .message("Invalid transaction ID")
                        .parameterValue("transaction").build());
        }
        if(category != null)
            errors.addAll(categoryValidator.validateCategory(category, "Invalid category"));
        if(!errors.isEmpty()){
            throw ValidationException.builder()
                    .message("Failed to update category")
                    .errors(errors).build();
        }
        try{
            Transaction transaction = transactionDao.queryForId(transactionID);
            if(transaction == null){
                throw ValidationException.builder().error(ValidationError.builder()
                        .message("Invalid transaction")
                        .parameterName("transaction")
                        .parameterValue(transactionID.toString()).build())
                        .message("Failed to update category").build();
            } else {
                transaction.setCategory(category);
                transactionDao.update(transaction);
                return transactionDao.queryForId(transaction.getID());
            }
        } catch (SQLException e){
            logger.error(e);
            throw new DatabaseException(e);
        }
    }
}
