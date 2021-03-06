package com.cloud.scaffold.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@SentinelResource(value = "testController")
public class TestController {

  @Value("${test.value:}")
  private String testValue;

  @GetMapping("/testValue")
  public String testValue() {
    return "test value is:" + testValue;
  }

  @GetMapping("/get")
  public String getMsg(@RequestParam("name") String name) {
    return "hello:" + name;
  }
}
