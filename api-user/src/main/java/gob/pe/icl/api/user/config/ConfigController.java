package gob.pe.icl.api.user.config;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"gob.pe.icl.service"})
@RibbonClient(name = "api-user-dev")
public class ConfigController {

}
