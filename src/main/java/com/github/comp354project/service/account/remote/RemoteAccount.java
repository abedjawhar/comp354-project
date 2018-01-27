package com.github.comp354project.service.account.remote;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RemoteAccount {
    private String ID;
    private String bankName;
    private String type;
    private String balance;
    private String currency;
    private List<RemoteTransaction> transactions;
}
