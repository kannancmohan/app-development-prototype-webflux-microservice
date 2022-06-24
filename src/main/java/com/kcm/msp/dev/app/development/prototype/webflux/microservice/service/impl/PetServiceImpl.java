package com.kcm.msp.dev.app.development.prototype.webflux.microservice.service.impl;

import com.kcm.msp.dev.app.development.prototype.webflux.microservice.models.Pet;
import com.kcm.msp.dev.app.development.prototype.webflux.microservice.service.PetService;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PetServiceImpl implements PetService {

  @Override
  public Flux<Pet> listPets(final Integer limit) {
    if (limit == null || limit < 0) {
      return Flux.empty();
    }
    List<Pet> results = new ArrayList<>();
    for (int i = 0; i < 8000; i++) {
      results.add(new Pet().id((long) i).name("petName").tag("petTag"));
    }
    return Flux.fromIterable(results);
  }

  public Flux<Pet> emitPets() {
    return Flux.interval(Duration.ofSeconds(1))
        .map(val -> new Pet().id(val).name("petName").tag("petTag").dateOfBirth(LocalDate.now()));
  }

  @Override
  public Mono<Pet> showPetById(final String id) {
    if (StringUtils.isBlank(id)) {
      return Mono.empty();
    }
    return Mono.just(new Pet().id(123L).name("petName").tag("petTag").dateOfBirth(LocalDate.now()));
  }
}
