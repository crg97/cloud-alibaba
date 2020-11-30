package com.cloud.scaffold.controller;

import com.cloud.scaffold.api.TokenClient;
import com.cloud.scaffold.dto.LoginDTO;
import com.cloud.scaffold.model.User;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

  private List<User> userList = new ArrayList<>();

  @PostConstruct
  public void initUser() {
    List<User> userList = new ArrayList<>();
    User eddie = new User("eddie", "123456", Collections.singletonList("admin"));
    User joker = new User("joker", "123456", Collections.singletonList("admin"));
    userList.addAll(Arrays.asList(eddie, joker));
    this.userList = userList;
  }

  @Resource
  private TokenClient tokenClient;

  @PostMapping("/login")
  public String loginToken(@RequestBody LoginDTO loginDTO) {
    Optional<User> user = userList.stream().filter(item -> StringUtils.equals(loginDTO.getUsername(), item.getUsername())).findFirst();
    if (!user.isPresent() || !StringUtils.equals(user.get().getPassword(), loginDTO.getPassword())) {
      return "账户不存在或密码错误";
    }
    Map<String, String> params = new HashMap<>();
    params.put("client_id", "provider_1");
    params.put("client_secret", "secret");
    params.put("grant_type", "password");
    params.put("username", loginDTO.getUsername());
    params.put("password", loginDTO.getPassword());
    return tokenClient.getToken(params);
  }

}