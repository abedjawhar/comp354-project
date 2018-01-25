package com.github.comp354project.dao.budget;

import com.github.comp354project.dao.budget.impl.AccountDao;
import dagger.Module;
import dagger.Provides;

/**
 * Budget DAO module for database accesses for classes linked to budget
 */
@Module
public class BudgetDaoModule {

    @Provides
    static IAccountDao provideAccountDao() {
        return new AccountDao();
    }
}
