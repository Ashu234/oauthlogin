package com.oauth.login.service;


import com.oauth.login.repository.IConnectionRepo;
import org.springframework.beans.factory.annotation.Autowired;

public class DataService {

    @Autowired
    private IConnectionRepo iConnectionRepo;

    public IConnectionRepo getFileRepo() {
        return iConnectionRepo;
    }

}
