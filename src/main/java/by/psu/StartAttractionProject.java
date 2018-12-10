package by.psu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@EntityScan(value = {"by.psu.model", "by.psu.security.model"})
public class StartAttractionProject extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(StartAttractionProject.class, args);
    }

/*    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(StartAttractionProject.class);
    }*/
}