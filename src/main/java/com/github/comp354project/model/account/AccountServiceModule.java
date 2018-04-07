package com.github.comp354project.model.account;

import com.github.comp354project.utils.ProxyFactory;
import dagger.Module;
import dagger.Provides;

@Module
public class AccountServiceModule {
    @Provides static IAccountService provideAccountService(AccountService accountService) {
        return ProxyFactory.newInstance(accountService, IAccountService.class);
    }
    @Provides static ITransactionService provideTransactionService(TransactionService transactionService){
        return ProxyFactory.newInstance(transactionService, ITransactionService.class);
    }
}
