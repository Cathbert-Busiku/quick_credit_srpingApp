package com.quick_credit.quick_credit.service.serviceImp;

import com.quick_credit.quick_credit.entity.Account;
import com.quick_credit.quick_credit.repository.AccountRepository;
import com.quick_credit.quick_credit.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class AccountServiceImp implements AccountService {
    @Autowired
    AccountRepository accountRepository;
    @Override
    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account updateAccount(Account account) {
        if (account.getBalance() < 0){
            throw new IllegalArgumentException("Balance+amount cannot be less than zero");
        }
        return accountRepository.save(account);
    }
}
