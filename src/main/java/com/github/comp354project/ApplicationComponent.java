package com.github.comp354project;

import com.github.comp354project.service.account.AccountServiceModule;
import com.github.comp354project.service.account.remote.RemoteAccountModule;
import com.github.comp354project.service.auth.AuthenticationModule;
import com.github.comp354project.service.dao.DaoModule;
import com.github.comp354project.service.sqlite.ConnectionModule;
import com.github.comp354project.service.user.UserServiceModule;
import com.github.comp354project.viewController.AccountListController;
import com.github.comp354project.viewController.LoginController;
import com.github.comp354project.viewController.SignUpController;
import com.github.comp354project.viewController.view.TransactionTableController;
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
        AuthenticationModule.class
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
}
