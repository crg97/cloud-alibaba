package com.cloud.scaffold;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringCloudApplication
@ComponentScan(basePackages = "com.cloud.scaffold")
@EnableFeignClients
public class CloudGatewayApplication {

  public static void main(String[] args) {
    SpringApplication.run(CloudGatewayApplication.class, args);
  }

}
