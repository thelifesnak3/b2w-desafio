package com.github.thelifesnak3.b2w.repository;

import com.github.thelifesnak3.b2w.entities.Planeta;
import io.quarkus.mongodb.panache.PanacheMongoRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PlanetaRepository implements PanacheMongoRepository<Planeta> {

    public Planeta findByName(String nome) {
        return find("nome", nome).firstResult();
    }
}
