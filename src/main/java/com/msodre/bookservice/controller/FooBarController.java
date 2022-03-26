package com.msodre.bookservice.controller;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("book-service")
public class FooBarController {

  private Logger logger = LoggerFactory.getLogger(FooBarController.class);

  @GetMapping("/foo-bar")
  //    @Retry(name = "foo-bar", fallbackMethod = "fallbackMethod")
  //    @CircuitBreaker(name = "default", fallbackMethod = "fallbackMethod")
//  @RateLimiter(name = "default")
  @Bulkhead(name = "default")
  public String fooBar() {
    logger.info("Request to foo-bar is received");
//    var response = new RestTemplate().getForEntity("http://localhost:8080/foobar", String.class);
//    return response.getBody();
    return "Foo-bar!!!";
  }

  public String fallbackMethod(Exception e) {
    return "fallbackMethod foo-bar";
  }
}
