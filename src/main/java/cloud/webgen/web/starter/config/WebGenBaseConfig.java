package cloud.webgen.web.base.starter.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan("cloud.webgen.web.base.starter.handler")
@ComponentScan("cloud.webgen.web.base.starter.controller")
@ComponentScan("cloud.webgen.web.base.starter.utils")
public class WebGenBaseConfig implements WebMvcConfigurer {

    @Value("${cors.allowed-origin}")
    private String origin;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(origin)
                .allowedMethods("PUT", "DELETE", "POST", "GET", "OPTIONS")
                .allowedHeaders("Authorization", "Content-Type")
                .allowCredentials(true)
                .maxAge(3600);
    }
}
