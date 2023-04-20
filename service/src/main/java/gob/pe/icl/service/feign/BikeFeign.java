package gob.pe.icl.service.feign;
/*
import com.jofrantoba.model.jpa.shared.UnknownException;
import feign.FeignException;
import gob.pe.icl.entity.Bike;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "api-bike-dev")
@Component
public interface BikeFeign {

    @Retryable(value = {FeignException.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 3000, maxDelay = 10000, multiplier = 2))
    @PostMapping("/bike")
    Bike saveBike(@RequestBody Bike bike) throws UnknownException;
    @Retryable(value = {FeignException.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 3000, maxDelay = 10000, multiplier = 2))
    @GetMapping("/bike/user/{userId}")
    List<Bike> findBikesByUserId(@PathVariable("userId") Long userId) throws UnknownException;
}

 */
