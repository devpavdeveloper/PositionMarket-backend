package by.psu.config;

import by.psu.reporting.ExcelBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class GenericConfig {
    @Bean
    public ExcelBuilder xmlBuilder(){
        return new ExcelBuilder();
    }
}

