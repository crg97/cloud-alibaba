package com.cloud.scaffold;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringCloudApplication
@ComponentScan(basePackages = "com.cloud.scaffold")
public class CloudProvider1Application {

	public static void main(String[] args) {
		SpringApplication.run(CloudProvider1Application.class, args);
	}

}
