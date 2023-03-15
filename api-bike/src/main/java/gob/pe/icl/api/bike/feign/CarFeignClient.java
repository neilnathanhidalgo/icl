package gob.pe.icl.api.bike.feign;

import gob.pe.icl.entity.Car;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "api-car-dev", url = "http://localhost:9002")
@RequestMapping("/car")
public interface CarFeignClient {

    @PostMapping()
    Car saveCar(@RequestBody Car car);
}
