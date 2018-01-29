package com.github.comp354project.service.account.remote;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RemoteTransaction {
    private Integer ID;
    private Integer accountID;
    private Integer date;
    private Double amount;
    private String currency;
    private String type;
    private Integer sourceID;
    private Integer destinationID;
}
