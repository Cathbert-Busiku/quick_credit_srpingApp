package com.quick_credit.quick_credit.service.serviceImp;

import com.quick_credit.quick_credit.entity.Deposit;
import com.quick_credit.quick_credit.entity.User;
import com.quick_credit.quick_credit.repository.DepositRepository;
import com.quick_credit.quick_credit.service.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepositServiceImpl implements DepositService {
    @Autowired
    private DepositRepository depositRepository;

    @Override
    public Deposit saveDeposit(Deposit deposit) {

        if (deposit.getDeposit() < 0){
            throw new IllegalArgumentException("Deposit amount cannot be less than zero");
        }
        return depositRepository.save(deposit);
    }

    @Override
    public List<Deposit> getAllDeposits(User user) {
        return depositRepository.findDeposits(user);
    }


}
