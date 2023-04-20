package gob.pe.icl.oauth.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Usuario
 */
@FeignClient(name = "api-user-dev")
@Component
public interface UserFeign {

    @PostMapping(value="//user/find/{username}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    String getUserByUsername(@PathVariable("username") String username) throws Exception;

}