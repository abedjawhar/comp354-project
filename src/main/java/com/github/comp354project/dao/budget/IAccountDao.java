package com.github.comp354project.dao.budget;

/**
 * Query/create data for accounts (bank, credit card, loans, etc)
 */
public interface IAccountDao {

    /**
     * Fetches the total balance of a user
     * @return
     */
    Double getBalance();
}
