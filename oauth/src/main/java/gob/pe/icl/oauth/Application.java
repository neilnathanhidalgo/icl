/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gob.pe.icl.oauth;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.security.crypto.keygen.Base64StringKeyGenerator;
import org.springframework.security.crypto.keygen.StringKeyGenerator;


/**
 *
 * @author Usuario
 */
@Slf4j
@RefreshScope
@SpringBootApplication
public class Application {
    

    public static void main(String[] args) throws NoSuchAlgorithmException {
        SpringApplication.run(Application.class, args);
        String codeVerifier=createCodeVerifier();
        log.info("code verifier:"+codeVerifier);
        log.info("code_challenge:"+createCodeChallenge(codeVerifier));
    }
    
    private static String createCodeChallenge(String value) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] digest = md.digest(value.getBytes(StandardCharsets.US_ASCII));
        return Base64.getUrlEncoder().withoutPadding().encodeToString(digest);
    }
    
    private static String createCodeVerifier(){        
        StringKeyGenerator secureKeyGenerator =
        new Base64StringKeyGenerator(Base64.getUrlEncoder().withoutPadding(), 96);
        return secureKeyGenerator.generateKey();
    }
}
