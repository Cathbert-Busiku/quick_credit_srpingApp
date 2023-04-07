package com.quick_credit.quick_credit.repository;

import com.quick_credit.quick_credit.entity.User;
import com.quick_credit.quick_credit.entity.Withdraw;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface WithdrawRepository extends JpaRepository<Withdraw,Long> {
    @Query("FROM Withdraw WHERE user=:user")
    List<Withdraw> findWithdraws(@Param("user") User user);

}
