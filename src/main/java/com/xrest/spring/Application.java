package com.xrest.spring;

import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.Arrays;

@SpringBootApplication
public class Application {
    @Value("${spring.application.name}")
    private String applicationName;
//    @Autowired
//    CloseableHttpClient httpClient;
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

//    @PostConstruct
//    public void hitReq() {
//        String uri = "https://dummy.restapiexample.com/api/v1/employees";
////        HttpHeaders headers = new HttpHeaders();
////        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
////        headers.set("X-COM-PERSIST", "NO");
////        headers.set("X-COM-LOCATION", "USA");
//
//        Object data = restTemplate().getForObject(uri,Object.class);
//        System.out.println(data.toString());
//    }
//    @Bean
//    public RestTemplate restTemplate() {
//        RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory());
//        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory("https://dummy.restapiexample.com/api/v1"));
//        return restTemplate;
//    }
//
//    @Bean
//    @ConditionalOnMissingBean
//    public HttpComponentsClientHttpRequestFactory clientHttpRequestFactory() {
//        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory
//                = new HttpComponentsClientHttpRequestFactory();
//        clientHttpRequestFactory.setHttpClient(httpClient);
//        return clientHttpRequestFactory;
//    }

}
