package gob.pe.icl.oauth.config.demo1;

import com.nimbusds.jose.jwk.RSAKey;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

import org.springframework.stereotype.Component;

/**
 *
 * @author Usuario
 */
@Component
public class KeyManager {

    public RSAKey rsaKey() throws NoSuchAlgorithmException{
        KeyPairGenerator kpg=KeyPairGenerator.getInstance("RSA");
        kpg.initialize(2048);
        KeyPair kp=kpg.generateKeyPair();
        RSAPublicKey publicKey=(RSAPublicKey) kp.getPublic();
        RSAPrivateKey privateKey=(RSAPrivateKey) kp.getPrivate();
        return new RSAKey.Builder(publicKey).privateKey(privateKey).keyID(UUID.randomUUID().toString()).build();
    }
}