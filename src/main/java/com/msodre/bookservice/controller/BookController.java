package com.msodre.bookservice.controller;

import com.msodre.bookservice.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book-service")
public class BookController {

  private final Environment environment;

  @GetMapping("/{id}/{currency}")
  public Book findBook(@PathVariable Long id, @PathVariable String currency) {
    var port = environment.getProperty("local.server.port");
    return Book.builder()
        .id(1L)
        .author("Nigel Poulton")
        .launchDate(LocalDateTime.now())
        .price(new BigDecimal("15.8"))
        .title("Docker Deep Dive")
        .currency(currency)
        .environment(port)
        .build();
  }
}
