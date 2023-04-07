package com.quick_credit.quick_credit.repository;

import com.quick_credit.quick_credit.entity.Deposit;
import com.quick_credit.quick_credit.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DepositRepository extends JpaRepository<Deposit,Long> {

    @Query("FROM Deposit WHERE user=:user")
    List<Deposit> findDeposits(@Param("user") User user);
}
