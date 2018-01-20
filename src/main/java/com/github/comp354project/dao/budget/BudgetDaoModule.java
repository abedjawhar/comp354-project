package com.github.comp354project.dao.budget;

import com.github.comp354project.dao.budget.impl.BankAccountDao;
import dagger.Module;
import dagger.Provides;

/**
 * Budget DAO module for database accesses
 */
@Module
public class BudgetDaoModule {

    @Provides
    static IBankAccountDao provideBankAccountService() {
        return new BankAccountDao();
    }
}
