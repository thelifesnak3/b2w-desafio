package com.github.thelifesnak3.b2w.service;

import com.github.thelifesnak3.b2w.dto.AdicionarPlanetaDTO;
import com.github.thelifesnak3.b2w.dto.PlanetaDTO;
import com.github.thelifesnak3.b2w.dto.SwapiDTO;
import com.github.thelifesnak3.b2w.entities.Planeta;
import com.github.thelifesnak3.b2w.mapper.PlanetaMapper;
import com.github.thelifesnak3.b2w.repository.PlanetaRepository;
import org.bson.types.ObjectId;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class PlanetaService {

    @Inject
    PlanetaRepository planetaRepository;

    @Inject
    @RestClient
    SwapiService swapiService;

    @Inject
    PlanetaMapper planetaMapper;

    public List<Planeta> find(String nome) {
        if(nome != null) {
            return planetaRepository.find("nome", nome).list();
        }
        return planetaRepository.findAll().list();
    }

    public PlanetaDTO findById(String id) {
        if(!ObjectId.isValid(id)) {
            throw new BadRequestException("O id informado é inválido");
        }

        ObjectId planetaId = new ObjectId(id);

        Optional<Planeta> planetaOp = planetaRepository.findByIdOptional(planetaId);
        if(planetaOp.isEmpty()) {
            throw new NotFoundException();
        }

        return planetaMapper.toPlanetaDTO(planetaOp.get());
    }

    @Transactional
    public void add(AdicionarPlanetaDTO adicionarPlanetaDTO) {
        SwapiDTO swapiDTO = swapiService.getPlanet(adicionarPlanetaDTO.nome);
        if(swapiDTO.count == 0) {
            throw new BadRequestException("Não foi possível encontrar o nome informado na SWAPI");
        }

        Optional<Planeta> planetaOp = planetaRepository.find("nome", adicionarPlanetaDTO.nome).firstResultOptional();
        if(!planetaOp.isEmpty()) {
            throw new BadRequestException("O nome informado já foi cadastrado");
        }

        Planeta planeta = planetaMapper.toPlaneta(adicionarPlanetaDTO);
        planeta.aparicoes = swapiDTO.getAparicoes();
        planetaRepository.persist(planeta);
    }

    public void delete(String id) {
        ObjectId planetaId = new ObjectId(id);
        Optional<Planeta> planetaOp = planetaRepository.findByIdOptional(planetaId);

        if(planetaOp.isEmpty()) {
            throw new NotFoundException();
        }

        planetaRepository.delete(planetaOp.get());
    }
}
