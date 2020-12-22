package com.project.demo.service;

import com.project.demo.ApplicationException;
import com.project.demo.exception.InvalidCredentialException;
import com.project.demo.model.Credential;
import com.project.demo.model.User;
import com.project.demo.model.UserToken;
import org.springframework.stereotype.Service;
import java.util.concurrent.CompletableFuture;


public interface LoginRequestService {
    User authenticate(Credential credential) throws InvalidCredentialException, InterruptedException;
    UserToken issueToken(User user) throws InvalidCredentialException, InterruptedException;
    CompletableFuture<UserToken> requestToken(Credential credential) throws InvalidCredentialException;
}
