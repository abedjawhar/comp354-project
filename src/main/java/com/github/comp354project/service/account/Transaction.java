package com.github.comp354project.service.account;

import lombok.Data;

import java.util.Date;

@Data
public class Transaction {
    private Integer ID;
    private Integer accountID;
    private Date date;
    private Double amount;
    private String type;
    private String category;
    private Integer sourceID;
    private Integer destinationID;
}
