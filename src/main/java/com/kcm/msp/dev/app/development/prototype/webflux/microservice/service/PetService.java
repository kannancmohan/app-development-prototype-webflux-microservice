package com.kcm.msp.dev.app.development.prototype.webflux.microservice.service;

import com.kcm.msp.dev.app.development.prototype.webflux.microservice.models.Pet;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PetService {
  Flux<Pet> listPets(Integer limit);

  Flux<Pet> emitPets();

  Mono<Pet> showPetById(String id);
}
