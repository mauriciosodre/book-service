package com.msodre.bookservice.integration.cambiointegration;

import com.msodre.bookservice.integration.cambiointegration.model.Cambio;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Optional;

@Service
public class CambioIntegration {
  private static final String URL = "http://localhost:8000/cambio-service";
  private static final String ENDPOINT = "/{amount}/{from}/{to}";

  public Optional<Cambio> getCambio(BigDecimal amount, String from, String to) {
    var params = new HashMap<String, String>();
    params.put("amount", amount.toString());
    params.put("from", from);
    params.put("to", to);
    return Optional.ofNullable(
        new RestTemplate().getForEntity(URL.concat(ENDPOINT), Cambio.class, params).getBody());
  }
}
