package gob.pe.icl.oauth.service.feign;

import feign.FeignException;
import gob.pe.icl.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "api-user-dev")
public interface UserFeign {

    @Retryable(value = {FeignException.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 3000, maxDelay = 10000, multiplier = 2))
    @GetMapping("/user/{username}")
    User fingByUsername(@PathVariable("username") String username);
}
