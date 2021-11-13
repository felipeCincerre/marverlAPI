package com.growin.marvel.configuration;

import java.sql.Timestamp;

import com.growin.marvel.utils.MarvelUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

@Configuration
public class AuthConfiguration {

    @Value("${api.privateKey}")
    private String privateKey;

    @Value("${api.publicKey}")
    private String publicKey;

    private String ts = String.valueOf(new Timestamp(System.currentTimeMillis()).getTime());

    private String hash = null;

    @Bean
    public String authPublicKey() {
        return publicKey;
    }

    @Bean
    public String authTs() {
        return ts;
    }

    @Bean
    public String authHash(){
        if(!StringUtils.hasText(hash)){
            hash = MarvelUtils.retriveMD5(ts, privateKey, publicKey);
        }
        return hash;
    }

}