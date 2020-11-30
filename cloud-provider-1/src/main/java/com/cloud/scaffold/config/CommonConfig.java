package com.cloud.scaffold.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ResourceServerConfig.class})
public class CommonConfig {

}
