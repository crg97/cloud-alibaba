package com.cloud.scaffold;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin2.server.internal.EnableZipkinServer;

@SpringBootApplication
@EnableZipkinServer
public class CloudMonitorApplication {

  public static void main(String[] args) {
    SpringApplication.run(CloudMonitorApplication.class, args);
  }

}
