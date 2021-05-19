package com.github.thelifesnak3.b2w.unit;

import com.github.thelifesnak3.b2w.entities.Planeta;
import com.github.thelifesnak3.b2w.repository.PlanetaRepository;
import com.github.thelifesnak3.b2w.service.PlanetaService;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import java.util.Optional;

@QuarkusTest
public class PlanetaServiceFindByIdTest {

    @Inject
    PlanetaService planetaService;

    @InjectMock
    PlanetaRepository mockPlanetaRepository;

    private final String idValid = "60a55a4e02446209bb771942";
    private final String idInvalid = "123";

    @BeforeEach
    public void setup() {

    }

    @Test
    @DisplayName("Verificando se ao chamar o serviço de findById com id inválido é lançada a " +
            "exceção corretamente")
    public void testFindByIdThrowBadRequest() {
        BadRequestException exception = Assertions.assertThrows(BadRequestException.class, () ->
            planetaService.findById(idInvalid)
        );

        Assertions.assertEquals(
            planetaService.idInvalidoException,
            exception.getMessage()
        );
    }

    @Test
    @DisplayName("Verificando se ao chamar o serviço de findById é lançada a exceção de NotFoundException" +
            " caso não encontre o valor esperado")
    public void testFindByIdThrowNotFoundException() {
        Mockito.when(mockPlanetaRepository.findByIdOptional(Mockito.any()))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoundException.class, () ->
            planetaService.findById(idValid)
        );
    }

    @Test
    @DisplayName("Verificando se ao chamar o serviço de findById com os parametros corretos, " +
            "não é lançada nenhuma exception")
    public void testFindByIdDoesntThrow() {
        Mockito.when(mockPlanetaRepository.findByIdOptional(Mockito.any()))
                .thenReturn(Optional.of(new Planeta()));

        Assertions.assertDoesNotThrow(() ->
            planetaService.findById(idValid)
        );
    }
}
