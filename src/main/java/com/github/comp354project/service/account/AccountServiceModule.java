package com.github.comp354project.service.account;

import dagger.Module;
import dagger.Provides;

@Module
public class AccountServiceModule {

    @Provides static IAccountService provideAccountService(AccountService accountService) {
        return accountService;
    }
}
