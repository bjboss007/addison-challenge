package com.project.demo.service;

import com.project.demo.ApplicationException;
import com.project.demo.exception.InvalidCredentialException;
import com.project.demo.model.Credential;
import com.project.demo.model.User;
import com.project.demo.model.UserToken;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
@Service
public class LoginRequestServiceImpl implements LoginRequestService{

    @Override
    public User authenticate(Credential credential) throws InvalidCredentialException, InterruptedException {
        if (validateCredential(credential)) {
            delay(500);
            return new User(credential.getUsername());
        } else{
            throw  new InvalidCredentialException("Invalid Credentials");
        }

    }

    @Override
    public UserToken issueToken(User user) throws InvalidCredentialException, InterruptedException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        if (!user.getUserId().startsWith("A")) {
            String date = dateFormat.format(new Date());
            String token = user.getUserId() + "_" + date;
            delay(500);
            return new UserToken(token);
        } else {
            throw new InvalidCredentialException("Invalid Username");
        }

    }

    @Override
    public CompletableFuture<UserToken> requestToken(Credential credential) throws InvalidCredentialException {
        CompletableFuture<UserToken> userToken = new CompletableFuture<>();
        try {
            User user = authenticate(credential);
            if(user != null){
                UserToken token = issueToken(user);
                if (token != null){
                    Executors.newCachedThreadPool().submit(() -> {
                        delay(500);
                        userToken.complete(token);
                        return null;
                    });
                }
            }
        }catch (Exception e){
            throw new InvalidCredentialException(e.getMessage());
        }
        return userToken;
    }

    private boolean validateCredential(Credential credential){
        return credential.getUsername().toUpperCase().equals(credential.getPassword());
    }

    private void delay(int time) throws InterruptedException {
        Thread.sleep(time);
    }
}
