package com.epam.demo;

import com.epam.demo.configuration.ElasticConfig;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(ElasticConfig.class)
public class TestApplication {
}
