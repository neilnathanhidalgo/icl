package gob.pe.icl.oauth.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import gob.pe.icl.oauth.feign.UserFeign;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;
/**
 *
 * @author Usuario
 */
@Service
public class MyUserDetailsService implements UserDetailsService{

    @Autowired
    UserFeign userFeign;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        String user=null;
        String password=null;
        try {
            user = userFeign.getUserByUsername(username);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(user);
            password=jsonNode.get("password").toString();
        } catch (Exception ex) {
            Logger.getLogger(MyUserDetailsService.class.getName()).log(Level.SEVERE, null, ex);
            throw new UsernameNotFoundException("No se pudo conectar al servicio users: " + username);
        }
        if (user == null || password==null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        UserDetails userDetails = User.withDefaultPasswordEncoder().username(username).password(password)
                .roles("USER")
                .build();
        return userDetails;
        /*return org.springframework.security.core.userdetails.User.builder()
                .username(username)
                .password(password)
                .authorities("ROLE_USER")
                .build();*/
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}