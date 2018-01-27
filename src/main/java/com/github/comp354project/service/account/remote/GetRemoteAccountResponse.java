package com.github.comp354project.service.account.remote;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetRemoteAccountResponse {
    private RemoteAccount account;
}
