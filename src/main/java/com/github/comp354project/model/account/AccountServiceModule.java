package com.github.comp354project.model.account;

import dagger.Module;
import dagger.Provides;

@Module
public class AccountServiceModule {
    @Provides static IAccountService provideAccountService(AccountService accountService) {
        return accountService;
    }
    @Provides static ITransactionService provideTransactionService(TransactionService transactionService){return transactionService;}
}
