package com.msodre.bookservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity(name = "book")
public class Book implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable = false, length = 180)
  private String author;
  @Column(nullable = false)
  private LocalDateTime launchDate;
  @Column(nullable = false)
  private BigDecimal price;
  @Column(nullable = false, length = 250)
  private String title;

  @Transient
  private String currency;
  @Transient
  private String environment;
}
