package com.covid19.authservice;
/**
 *
 * Modified by Peter Fight
 *
 */

import com.covid19.authservice.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableDiscoveryClient
public class AuthServiceApplication {

    @Autowired
    private UserServiceImpl serv;
    public static void main(String[] args) {

        SpringApplication.run(AuthServiceApplication.class, args);



    }

    /**
     These java stuff is cool. So I have a voidMain without complications and with an annotation
     I put what comes out of the drawers. Well, stop. It is necessary to prepare the db, because this seems to me
     a chachi site.
     */
    @PostConstruct
    private void initPrettyThingsDB() {
        serv.initPrettyThingsDb();
    }
}
