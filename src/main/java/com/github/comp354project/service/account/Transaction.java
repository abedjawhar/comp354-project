package com.github.comp354project.service.account;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Transaction {
    private Integer ID;
    private Integer accountID;
    private Date date;
    private Double amount;
    private String type;
    private String category;
    private String currency;
    private Integer sourceID;
    private Integer destinationID;
}
