package com.github.comp354project.service.account;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Account {
    private Integer ID;
    private Integer userID;
    private String bankName;
    private String type;
    private Double balance;
    private String currency;
    private List<Transaction> transactions;
}
