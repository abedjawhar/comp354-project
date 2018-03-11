package com.github.comp354project.service.auth.exceptions;

import lombok.Getter;
import lombok.Builder;

public class AuthenticationException extends Exception{

   @Getter
   private String username;

   @Getter
   private String password;

   @Builder
   public AuthenticationException(String message, Throwable cause, String username, String password){
        super(message, cause);
        this.username = username;
        this.password = password;
   }
}
