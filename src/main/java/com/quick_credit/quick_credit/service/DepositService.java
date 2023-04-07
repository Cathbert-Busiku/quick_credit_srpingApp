package com.quick_credit.quick_credit.service;

import com.quick_credit.quick_credit.entity.Deposit;
import com.quick_credit.quick_credit.entity.User;

import java.util.List;

public interface DepositService {
    public Deposit saveDeposit(Deposit deposit);

    List<Deposit> getAllDeposits(User user);
}
