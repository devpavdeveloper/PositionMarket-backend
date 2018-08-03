package by.psu;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication

public class StartAttractionProject {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(StartAttractionProject.class);

    public static void main(String[] args) {

        SpringApplication.run(StartAttractionProject.class, args);
        logger.info("Application deployed");
    }
}

