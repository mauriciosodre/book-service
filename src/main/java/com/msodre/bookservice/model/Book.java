package com.msodre.bookservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Book implements Serializable {

  private Long id;

  private String author;

  private LocalDateTime launchDate;

  private BigDecimal price;

  private String title;

  private String currency;

  private String environment;
}
