package com.github.comp354project.service.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
}
