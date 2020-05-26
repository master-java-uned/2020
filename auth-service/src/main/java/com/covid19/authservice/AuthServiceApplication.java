package com.covid19.authservice;

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
     * Estas cosas de java molan. Osea que tengo un voidMain sin complicaciones y con una anotación
     * le meto lo que me salga de los cAjones. Pues pa lante. Hace falta preparar la db, pues este me parece
     * un sitio chachi.
     */
    @PostConstruct
    private void initCosicasDB() {
        serv.initCosicasDb();
    }
}
