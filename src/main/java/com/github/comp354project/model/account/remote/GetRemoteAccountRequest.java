package com.github.comp354project.model.account.remote;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetRemoteAccountRequest {
    private Integer accountID;
}
