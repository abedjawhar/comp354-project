package com.github.comp354project.service.budget;

import javax.inject.Inject;

public class AccountDao  {

    @Inject
    public AccountDao() {}

    public Double getBalance() {
        return 12345.67;
    }
}
