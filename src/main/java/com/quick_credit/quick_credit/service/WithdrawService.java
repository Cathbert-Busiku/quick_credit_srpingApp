package com.quick_credit.quick_credit.service;

import com.quick_credit.quick_credit.entity.User;
import com.quick_credit.quick_credit.entity.Withdraw;

import java.util.List;

public interface WithdrawService {

   public Withdraw saveWithdraw(Withdraw withdraw);

   List<Withdraw> getAllWithdraws(User user);

}
