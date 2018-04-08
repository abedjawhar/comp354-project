package com.github.comp354project.model.email;

import com.github.comp354project.utils.ProxyFactory;
import com.github.comp354project.utils.Timing;
import dagger.Module;
import dagger.Provides;

import javax.inject.Named;

@Module
public class EmailServiceModule {
    @Provides
    static IEmailService provideCSVGenerator(EmailService emailService)  {
        return ProxyFactory.newInstance(emailService, IEmailService.class);
    }

    @Provides @Named("email.username") String provideUsername() {
        return "mymoneyappofficial@gmail.com";
    }

    @Provides @Named("email.password") String providePassword() {
        return "MyMoneyApp123";
    }
}
