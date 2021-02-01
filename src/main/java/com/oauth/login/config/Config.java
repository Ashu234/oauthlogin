package com.oauth.login.config;

import com.oauth.login.impl.FileDBImpl;
import com.oauth.login.repository.IConnectionRepo;
import com.oauth.login.repository.IUserSessionRepo;
import com.oauth.login.service.DataService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class Config {

    @Bean
    public DataService dataService() {
        return new DataService();
    }

    @Resource
    public IUserSessionRepo iUserSessionRepo;

    @Bean
    public IConnectionRepo iConnectionRepo() {
        return new FileDBImpl();
    }

}
