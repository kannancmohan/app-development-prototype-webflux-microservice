package com.kcm.msp.dev.app.development.prototype.webflux.microservice.service.impl;

import com.kcm.msp.dev.app.development.prototype.webflux.microservice.server.models.CreatePetRequest;
import com.kcm.msp.dev.app.development.prototype.webflux.microservice.server.models.Pet;
import com.kcm.msp.dev.app.development.prototype.webflux.microservice.service.PetService;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PetServiceImpl implements PetService {

  public static final Random RANDOM = new Random();
  private static final List<Pet> SAMPLE_PETS =
      Stream.iterate(1, n -> n + 1)
          .limit(21)
          .map(
              value ->
                  new Pet()
                      .id(Long.valueOf(value))
                      .name("petName" + value)
                      .tag("petTag" + value)
                      .dateOfBirth(LocalDate.now())
                      .ownerEmail("test" + value + "@email.com"))
          .collect(Collectors.toList());

  @Override
  public Flux<Pet> listPets(final Integer limit) {
    if (limit == null || limit < 0) {
      return Flux.empty();
    }
    List<Pet> results = SAMPLE_PETS.stream().limit(limit).collect(Collectors.toList());
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

  @Override
  public Mono<Pet> createPet(final CreatePetRequest request) {
    Objects.requireNonNull(request, "CreatePetRequest cannot be null");
    final Pet pet =
        new Pet()
            .id(RANDOM.nextLong())
            .name(request.getName())
            .tag(request.getTag())
            .dateOfBirth(request.getDateOfBirth())
            .ownerEmail(request.getOwnerEmail());
    SAMPLE_PETS.add(pet);
    return Mono.just(pet);
  }
}
