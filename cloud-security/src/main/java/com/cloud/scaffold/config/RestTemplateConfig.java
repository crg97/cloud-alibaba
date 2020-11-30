package com.cloud.scaffold.config;

import java.io.IOException;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

  @Bean
  @LoadBalanced
  RestTemplate restTemplate(){
    RestTemplate restTemplate = new RestTemplate();
    restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
      @Override
      public void handleError(ClientHttpResponse response) throws IOException {
        if (response.getRawStatusCode() != HttpStatus.BAD_REQUEST.value()) {
          super.handleError(response);
        }
      }
    });
    return restTemplate;
  }

}
