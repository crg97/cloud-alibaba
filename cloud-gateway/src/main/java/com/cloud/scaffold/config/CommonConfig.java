package com.cloud.scaffold.config;

import feign.codec.Decoder;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import reactor.core.publisher.Mono;

@Configuration
public class CommonConfig {

  @Bean
  public KeyResolver remoteAddrKeyResolver() {
    return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getAddress().getHostAddress());
  }

  @Bean
  public Decoder feignDecoder() {
    return new ResponseEntityDecoder(new SpringDecoder(feignHttpMessageConverter()));
  }

  public ObjectFactory<HttpMessageConverters> feignHttpMessageConverter() {
    MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
    List<MediaType> mediaTypes = new ArrayList<>();
    mediaTypes.add(MediaType.valueOf(MediaType.TEXT_HTML_VALUE + ";charset=UTF-8"));
    mappingJackson2HttpMessageConverter.setSupportedMediaTypes(mediaTypes);
    final HttpMessageConverters httpMessageConverters = new HttpMessageConverters(mappingJackson2HttpMessageConverter);
    return () -> httpMessageConverters;
  }


}
