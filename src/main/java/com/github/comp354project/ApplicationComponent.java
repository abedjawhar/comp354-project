package com.github.comp354project;

import com.github.comp354project.dao.budget.BudgetDaoModule;
import com.github.comp354project.service.budget.BudgetServiceModule;
import com.github.comp354project.viewController.PrimaryViewController;
import dagger.Component;

import javax.inject.Singleton;

@Component(modules={
        BudgetServiceModule.class,
        BudgetDaoModule.class
})
@Singleton
public interface ApplicationComponent {
    void inject(PrimaryViewController primaryViewController);
}
