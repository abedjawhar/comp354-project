package com.github.comp354project.model.account;

import com.github.comp354project.model.exceptions.ValidationException;

public interface ITransactionService {
    Transaction updateTransactionCategory(Integer transactionID, String category) throws ValidationException;
}
