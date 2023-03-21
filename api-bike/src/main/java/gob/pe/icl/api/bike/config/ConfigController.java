package gob.pe.icl.api.bike.config;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"gob.pe.icl.service"})
@EnableFeignClients(basePackages = "gob.pe.icl.service")
@RibbonClient(name = "api-bike-dev")
public class ConfigController {
}
