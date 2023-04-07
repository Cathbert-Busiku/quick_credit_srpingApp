package com.quick_credit.quick_credit.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="deposit")
public class Deposit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne()
    private User user;
    private Double deposit;
    private String phone;
    private String depositVerification;
    private String deposit_status;
    private Date created_at;

    @Transient
    public String getPhotosImagePath() {
        if (depositVerification == null || id == null) return null;

        return "/user-photos/" + id + "/" + depositVerification;
    }
}
