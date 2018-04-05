package com.github.comp354project.model.email;

import dagger.Module;
import dagger.Provides;

import javax.inject.Named;

@Module
public class EmailServiceModule {
    @Provides
    static IEmailService provideCSVGenerator(EmailService emailService)  {
        return emailService;
    }

    @Provides @Named("email.username") String provideUsername() {
        return "mymoneyappofficial@gmail.com";
    }

    @Provides @Named("email.password") String providePassword() {
        return "MyMoneyApp123";
    }
}
