package com.github.comp354project.model.account;

import com.github.comp354project.model.exceptions.ValidationException;
import com.github.comp354project.utils.Timing;

public interface ITransactionService {
    @Timing
    Transaction updateTransactionCategory(Integer transactionID, String category) throws ValidationException;
}
