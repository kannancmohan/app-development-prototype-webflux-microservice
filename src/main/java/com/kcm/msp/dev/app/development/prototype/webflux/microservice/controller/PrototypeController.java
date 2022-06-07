package com.kcm.msp.dev.app.development.prototype.webflux.microservice.controller;

import com.kcm.msp.dev.app.development.prototype.webflux.microservice.api.PrototypeApi;
import com.kcm.msp.dev.app.development.prototype.webflux.microservice.models.Pet;
import com.kcm.msp.dev.app.development.prototype.webflux.microservice.service.PetService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@AllArgsConstructor
public class PrototypeController implements PrototypeApi {

  private final PetService petService;

  @Override
  public Mono<ResponseEntity<Flux<Pet>>> listPets(Integer limit, ServerWebExchange exchange) {
    final Flux<Pet> petFlux = petService.listPets(limit);
    return petFlux
        .hasElements()
        .flatMap(
            isBoolean ->
                Boolean.TRUE.equals(isBoolean)
                    ? Mono.just(ResponseEntity.ok().body(petFlux))
                    : Mono.just(ResponseEntity.notFound().build()));
  }

  @Override
  public Mono<ResponseEntity<Pet>> showPetById(String petId, ServerWebExchange exchange) {
    return petService
        .showPetById(petId)
        .map(pet -> ResponseEntity.ok().body(pet))
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }
}
