package com.cloud.scaffold.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

  @Value("${test.value:}")
  private String testValue;

  @GetMapping("/testValue")
  public String testValue() {
    return "test value is:" + testValue;
  }

}
