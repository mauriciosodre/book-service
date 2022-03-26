package com.msodre.bookservice.controller;

import com.msodre.bookservice.integration.cambiointegration.CambioIntegration;
import com.msodre.bookservice.model.Book;
import com.msodre.bookservice.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book-service")
public class BookController {

  private final Environment environment;

  private final BookRepository repository;
  private final CambioIntegration cambioIntegration;

  @GetMapping("/{id}/{currency}")
  public Book findBook(@PathVariable Long id, @PathVariable String currency) {
    var port = environment.getProperty("local.server.port");

    var book = repository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));

    var cambio =
        cambioIntegration
            .getCambio(book.getPrice(), "USD", currency)
            .orElseThrow(() -> new RuntimeException("Currency not found"));

    book.setPrice(cambio.getConvertedValue());
    book.setEnvironment(port);

    return book;
  }
}
