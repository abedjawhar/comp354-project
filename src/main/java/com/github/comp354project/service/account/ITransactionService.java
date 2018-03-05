package com.github.comp354project.service.account;

import com.github.comp354project.service.exceptions.ValidationException;

public interface ITransactionService {
    Transaction updateTransactionCategory(Integer transactionID, String category) throws ValidationException;
}
