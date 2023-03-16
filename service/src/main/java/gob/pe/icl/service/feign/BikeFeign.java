package gob.pe.icl.service.feign;

import com.jofrantoba.model.jpa.shared.UnknownException;
import gob.pe.icl.entity.Bike;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "api-bike-dev")
@Component
public interface BikeFeign {

    @PostMapping("/bike")
    Bike saveBike(@RequestBody Bike bike) throws UnknownException;
}
