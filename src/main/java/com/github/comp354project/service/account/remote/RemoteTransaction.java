package com.github.comp354project.service.account.remote;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RemoteTransaction {
    private String ID;
    private String accountID;
    private String date;
    private String amount;
    private String type;
    private String sourceID;
    private String destinationID;
}
