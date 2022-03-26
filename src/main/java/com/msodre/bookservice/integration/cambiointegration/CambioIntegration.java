package com.msodre.bookservice.integration.cambiointegration;

import com.msodre.bookservice.integration.cambiointegration.model.Cambio;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;
import java.util.Optional;

@FeignClient(name = "cambio-service", url = "localhost:8000")
public interface CambioIntegration {

  @GetMapping("/cambio-service/{amount}/{from}/{to}")
  Optional<Cambio> getCambio(
      @PathVariable BigDecimal amount,
      @PathVariable String from,
      @PathVariable String to);
}
