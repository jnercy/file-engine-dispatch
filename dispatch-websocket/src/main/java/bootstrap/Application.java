package bootstrap;

import bootstrap.service.IWsExecuteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@SpringBootApplication
@EnableAutoConfiguration
@Configuration
@ImportResource({"classpath:spring-consumer.xml"})
@Controller
public class Application  extends SpringBootServletInitializer implements EmbeddedServletContainerCustomizer {

    @Autowired
    private IWsExecuteService wsExecuteService;


    @RequestMapping("/")
    @ResponseBody
    String home() {
        return wsExecuteService.transtion(null);
    }



    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        container.setPort(8081);
    }

}
