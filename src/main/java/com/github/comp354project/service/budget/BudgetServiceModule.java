package com.github.comp354project.service.budget;

import com.github.comp354project.service.budget.impl.BankAccountService;
import dagger.Module;
import dagger.Provides;

/**
 * Module for budgets
 */
@Module
public class BudgetServiceModule {

    @Provides static IBankAccountService provideBankAccountService(BankAccountService bankAccountService) {
        return bankAccountService;
    }
}
