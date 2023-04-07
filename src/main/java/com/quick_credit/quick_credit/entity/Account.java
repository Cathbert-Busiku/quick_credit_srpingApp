package com.quick_credit.quick_credit.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne()
    private User user;
    private Double balance;
    private Double bookBalance;
    private Double interest;
    private Double interestAccum;
    private Integer locked;
    private String status;
    private Date interest_at;

}
