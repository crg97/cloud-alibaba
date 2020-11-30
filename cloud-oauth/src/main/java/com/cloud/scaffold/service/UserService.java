package com.cloud.scaffold.service;

import com.cloud.scaffold.model.OauthUser;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class UserService implements UserDetailsService {
    private List<OauthUser> oauthUserList;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void initData() {
        String password = passwordEncoder.encode("123456");
        oauthUserList = new ArrayList<>();
        oauthUserList.add(new OauthUser("1", "eddie", password, Collections.singletonList("admin")));
        oauthUserList.add(new OauthUser("2", "joker", password, Collections.singletonList("admin")));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<OauthUser> findOauthUserList = oauthUserList.stream().filter(oauthUser -> oauthUser.getUsername().equals(username)).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(findOauthUserList)) {
            return findOauthUserList.get(0);
        } else {
            throw new UsernameNotFoundException("用户名或密码错误");
        }
    }
}