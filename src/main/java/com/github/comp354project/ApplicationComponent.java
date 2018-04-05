package com.github.comp354project;

import com.github.comp354project.model.account.AccountServiceModule;
import com.github.comp354project.model.account.remote.RemoteAccountModule;
import com.github.comp354project.model.auth.AuthenticationModule;
import com.github.comp354project.model.csv.CSVGeneratorModule;
import com.github.comp354project.model.dao.DaoModule;
import com.github.comp354project.model.sqlite.ConnectionModule;
import com.github.comp354project.model.user.UserServiceModule;
import com.github.comp354project.viewController.*;
import dagger.Component;

import javax.inject.Singleton;

/**
 * This class is used to 'connect all modules together'
 * It is also used to inject the classes that require this component
 *
 * All defined modules should be written here
 */
@Singleton
@Component(modules={
        AccountServiceModule.class,
        UserServiceModule.class,
        ConnectionModule.class,
        RemoteAccountModule.class,
        DaoModule.class,
        AuthenticationModule.class,
        CSVGeneratorModule.class
})
public interface ApplicationComponent {

    /**
     * The view needs classes defined in the services. The inject method
     * lets us do this.
     * Create an inject method for every different classes (controllers) in the views that would need them
     * @param myMoneyApplication
     */
    void inject(MyMoneyApplication myMoneyApplication);

    void inject(LoginController loginController);

    void inject(AccountListController accountListController);

    void inject(SignUpController signUpController);

    void inject(TransactionTableController tableController);

    void inject(UpdateUserAccountController updateUserAccountController);

    void inject(AllTransactionsController allTransactionsController);
}
