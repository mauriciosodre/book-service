package com.msodre.bookservice.controller;

import com.msodre.bookservice.model.Book;
import com.msodre.bookservice.integration.cambiointegration.CambioIntegration;
import com.msodre.bookservice.repository.BookRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Book Endpoint")
@RestController
@RequiredArgsConstructor
@RequestMapping("/book-service")
public class BookController {

  private final Environment environment;

  private final BookRepository repository;
  private final CambioIntegration cambioIntegration;

  @Operation(summary = "Find a specific book by your id")
  @GetMapping("/{id}/{currency}")
  public Book findBook(@PathVariable Long id, @PathVariable String currency) {
    var port = environment.getProperty("local.server.port");

    var book = repository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));

    var cambio =
        cambioIntegration
            .getCambio(book.getPrice(), "USD", currency)
            .orElseThrow(() -> new RuntimeException("Currency not found"));

    book.setPrice(cambio.getConvertedValue());
    book.setEnvironment("Book port: " + port + " Cambio port: " + cambio.getEnvironment());

    return book;
  }
}
