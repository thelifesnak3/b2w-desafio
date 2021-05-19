package com.github.thelifesnak3.b2w.unit;

import com.github.thelifesnak3.b2w.dto.AdicionarPlanetaDTO;
import com.github.thelifesnak3.b2w.dto.SwapiDTO;
import com.github.thelifesnak3.b2w.entities.Planeta;
import com.github.thelifesnak3.b2w.repository.PlanetaRepository;
import com.github.thelifesnak3.b2w.service.PlanetaService;
import com.github.thelifesnak3.b2w.service.SwapiService;
import io.quarkus.mongodb.panache.PanacheQuery;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import java.util.Optional;

@QuarkusTest
public class PlanetaServiceAddTest {

    @Inject
    PlanetaService planetaService;

    @InjectMock
    PlanetaRepository mockPlanetaRepository;

    @InjectMock
    @RestClient
    SwapiService mockSwapiService;

    private final String idValid = "60a55a4e02446209bb771942";
    private final String idInvalid = "123";

    @BeforeEach
    public void setup() {

    }

    @Test
    @DisplayName("Verificando se ao chamar o serviço de add com nome inexistente é lançado BadRequestException")
    public void testAddNameDoesntExist() {
        Mockito.when(mockSwapiService.getPlanet(Mockito.anyString()))
            .thenReturn(createEmptySwapiResponse());

        BadRequestException exception = Assertions.assertThrows(BadRequestException.class, () ->
            planetaService.add(createAdicionarDto(""))
        );

        Assertions.assertEquals(
            planetaService.nomeNotFoundSwapi,
            exception.getMessage()
        );
    }

    @Test
    @DisplayName("Verificando se ao passar um nome que já existe no banco de dados é retornado BadRequest informando")
    public void testAddNameDuplicated() {
        Mockito.when(mockSwapiService.getPlanet(Mockito.anyString()))
            .thenReturn(createFilledSwapiResponse());
        Mockito.when(mockPlanetaRepository.findByNome(Mockito.anyString()))
            .thenReturn(Optional.of(new Planeta()));

        BadRequestException exception = Assertions.assertThrows(BadRequestException.class, () ->
            planetaService.add(createAdicionarDto("312"))
        );

        Assertions.assertEquals(
            planetaService.nomeDuplicated,
            exception.getMessage()
        );
    }

    private AdicionarPlanetaDTO createAdicionarDto(String nome) {
        AdicionarPlanetaDTO adicionarPlanetaDTO = new AdicionarPlanetaDTO();
        adicionarPlanetaDTO.nome = nome;
        return adicionarPlanetaDTO;
    }

    private SwapiDTO createEmptySwapiResponse() {
        SwapiDTO swapiDTO = new SwapiDTO();
        swapiDTO.count = 0;
        return swapiDTO;
    }

    private SwapiDTO createFilledSwapiResponse() {
        SwapiDTO swapiDTO = new SwapiDTO();
        swapiDTO.count = 2;
        return swapiDTO;
    }
}
