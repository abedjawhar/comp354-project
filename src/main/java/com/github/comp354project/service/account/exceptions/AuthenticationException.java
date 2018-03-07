package com.github.comp354project.service.account.exceptions;

import lombok.Getter;
import lombok.Builder;

public class AuthenticationException extends RuntimeException{

   @Getter
   private String username;
   private String password;

   @Builder
   public AuthenticationException(String message, Throwable cause, String username, String password){
        super(message, cause);
        this.username = username;
        this.password = password;
   }
}
