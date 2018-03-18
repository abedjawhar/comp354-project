package com.github.comp354project.model.account.remote;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetRemoteAccountResponse {
    private RemoteAccount account;
}
