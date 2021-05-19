package com.github.thelifesnak3.b2w.repository;

import com.github.thelifesnak3.b2w.entities.Planeta;
import io.quarkus.mongodb.panache.PanacheMongoRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class PlanetaRepository implements PanacheMongoRepository<Planeta> {

    public Optional<Planeta> findByNome(String nome) {
        return find("nome", nome).firstResultOptional();
    }
}
