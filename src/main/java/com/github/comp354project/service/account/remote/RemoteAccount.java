package com.github.comp354project.service.account.remote;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.util.List;

@Data
@Builder
public class RemoteAccount {
    private Integer ID;
    private String bankName;
    private String type;
    private Double balance;
    private String currency;
    @Singular
    private List<RemoteTransaction> transactions;
}
