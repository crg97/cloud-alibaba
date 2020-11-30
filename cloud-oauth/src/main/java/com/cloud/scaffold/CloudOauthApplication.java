package com.cloud.scaffold;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringCloudApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = "com.cloud.scaffold")
public class CloudOauthApplication {

  public static void main(String[] args) {
    SpringApplication.run(CloudOauthApplication.class, args);
  }

}
