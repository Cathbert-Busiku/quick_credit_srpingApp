package com.quick_credit.quick_credit.service.serviceImp;

import com.quick_credit.quick_credit.entity.User;
import com.quick_credit.quick_credit.entity.Withdraw;
import com.quick_credit.quick_credit.repository.WithdrawRepository;
import com.quick_credit.quick_credit.service.WithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WithdrawServiceImpl implements WithdrawService {
    @Autowired
    private WithdrawRepository withdraeRepository;
    @Override
    public Withdraw saveWithdraw(Withdraw withdraw) {
        if(withdraw.getWithdraw() < 0){
            throw new IllegalArgumentException("Withdraw Amount can not be less than K 0.00");
        }
        return withdraeRepository.save(withdraw);
    }

    @Override
    public List<Withdraw> getAllWithdraws(User user) {
        return withdraeRepository.findWithdraws(user);
    }
}
