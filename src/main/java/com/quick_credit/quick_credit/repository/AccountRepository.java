package com.quick_credit.quick_credit.repository;

import com.quick_credit.quick_credit.entity.Account;
import com.quick_credit.quick_credit.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {
    Account findByUser(User user);
}
