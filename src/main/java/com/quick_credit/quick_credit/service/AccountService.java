package com.quick_credit.quick_credit.service;

import com.quick_credit.quick_credit.entity.Account;

public interface AccountService {
    Account saveAccount(Account account);
    Account updateAccount(Account account);

}
