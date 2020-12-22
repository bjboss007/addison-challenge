package com.project.demo;

import com.project.demo.exception.InvalidCredentialException;
import com.project.demo.model.Credential;
import com.project.demo.model.User;
import com.project.demo.model.UserToken;
import com.project.demo.service.LoginRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/v1/addison/")
public class LoginRequestController {

    @Autowired
    private LoginRequestService loginRequestService;


    @PostMapping("/login")
    public User validateCredential(@RequestBody Credential credential) throws InvalidCredentialException {
        try{
            return loginRequestService.authenticate(credential);
        }catch (InvalidCredentialException | InterruptedException ex){
            throw new InvalidCredentialException(ex.getMessage());
        }
    }

    @GetMapping("/issue-token")
    public  UserToken issueToke(@RequestBody User user) throws InvalidCredentialException{
        try{
            return loginRequestService.issueToken(user);
        }catch (Exception ex){
            throw  new InvalidCredentialException(ex.getMessage());
        }
    }

    @GetMapping("/requestToken")
    public UserToken requestUserToken(@RequestBody Credential credential) throws InvalidCredentialException {
        try {
            CompletableFuture<UserToken> userToken = loginRequestService.requestToken(credential);
            return userToken.get();
        }catch (Exception ex){
            throw  new InvalidCredentialException(ex.getMessage());
        }

    }
}
