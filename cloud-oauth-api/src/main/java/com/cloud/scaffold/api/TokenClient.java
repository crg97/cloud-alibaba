package com.cloud.scaffold.api;

import com.cloud.scaffold.constant.FeignConstant;
import java.util.Map;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = FeignConstant.CLOUD_OAUTH)
public interface TokenClient {

  @PostMapping("/oauth/token")
  String getToken(@RequestParam Map<String, String> parameters);

}
