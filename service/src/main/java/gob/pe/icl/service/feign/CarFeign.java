package gob.pe.icl.service.feign;

import com.jofrantoba.model.jpa.shared.UnknownException;
import gob.pe.icl.entity.Car;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "api-car-dev")
@Component
public interface CarFeign {

    @PostMapping("/car")
    Car saveCar(@RequestBody Car car) throws UnknownException;
}
