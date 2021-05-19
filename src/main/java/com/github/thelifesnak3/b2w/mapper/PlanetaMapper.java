package com.github.thelifesnak3.b2w.mapper;

import com.github.thelifesnak3.b2w.dto.AdicionarPlanetaDTO;
import com.github.thelifesnak3.b2w.dto.PlanetaDTO;
import com.github.thelifesnak3.b2w.entities.Planeta;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface PlanetaMapper {
    Planeta toPlaneta(AdicionarPlanetaDTO dto);
    PlanetaDTO toPlanetaDTO(Planeta p);
}
