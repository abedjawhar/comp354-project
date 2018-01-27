package com.github.comp354project.service.budget;

import dagger.Module;
import dagger.Provides;

/**
 * Module for budgets
 */
@Module
public class BudgetServiceModule {

    @Provides static IAccountService provideAccountService(AccountService accountService) {
        return accountService;
    }
}
