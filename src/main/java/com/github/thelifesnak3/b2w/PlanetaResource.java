package com.github.thelifesnak3.b2w;

import com.github.thelifesnak3.b2w.dto.AdicionarPlanetaDTO;
import com.github.thelifesnak3.b2w.dto.PlanetaDTO;
import com.github.thelifesnak3.b2w.entities.Planeta;
import com.github.thelifesnak3.b2w.service.PlanetaService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/planetas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PlanetaResource {

    @Inject
    PlanetaService planetaService;

    @GET
    @Operation(
        summary = "Realizar a busca dos planetas.",
        description = "Serviço responsável por buscar todos os planetas, ou o(s) planeta(s) cujo nome for especificado no query params.")
    @APIResponses(value = {
        @APIResponse(
            responseCode = "200",
            description = "Sucesso",
            content = @Content(mediaType = "application/json",
                schema = @Schema(
                    type = SchemaType.ARRAY,
                    implementation = Planeta.class))
        ),
        @APIResponse(
            responseCode = "204",
            description = "Nenhum dado encontrado"
        ),
        @APIResponse(
                responseCode = "500",
                description = "Erro interno da aplicação"
        )
    })
    public Response find(@QueryParam("nome") String nome) {
        List<Planeta> lista = planetaService.find(nome);
        return Response
            .status(lista.isEmpty() ? Response.Status.NO_CONTENT : Response.Status.OK)
            .entity(lista)
            .build();
    }

    @POST
    @Operation(
        summary = "Realizar o cadastro de planetas.",
        description = "Serviço responsável por cadastrar os planetas.")
    @APIResponses(value = {
        @APIResponse(
            responseCode = "200",
            description = "Sucesso"
        ),
        @APIResponse(
            responseCode = "400",
            description = "Bad Request:</br></br>" +
                "1 - Não foi possível encontrar o nome informado na SWAPI</br>" +
                "2 - O nome informado já foi cadastrado</br>"
        ),
        @APIResponse(
            responseCode = "500",
            description = "Erro interno da aplicação"
        )
    })
    public Response add(AdicionarPlanetaDTO adicionarPlanetaDTO) {
        planetaService.add(adicionarPlanetaDTO);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/{id}")
    @Operation(
        summary = "Realizar a busca de planetas por id.",
        description = "Serviço responsável por buscar o planeta de acordo com o id informado na url.")
    @APIResponses(value = {
        @APIResponse(
            responseCode = "200",
            description = "Sucesso",
            content = @Content(mediaType = "application/json",
                schema = @Schema(
                    implementation = PlanetaDTO.class))
        ),
        @APIResponse(
            responseCode = "400",
            description = "O id informado é inválido"
        ),
        @APIResponse(
            responseCode = "404",
            description = "Não foi possível encontrar o planeta informado"
        ),
        @APIResponse(
            responseCode = "500",
            description = "Erro interno da aplicação"
        )
    })
    public PlanetaDTO findById(@PathParam("id") String id) {
        return planetaService.findById(id);
    }

    @DELETE
    @Path("{id}")
    @APIResponses(value = {
        @APIResponse(
            responseCode = "204",
            description = "O planeta foi deletado com sucesso"
        ),
        @APIResponse(
            responseCode = "404",
            description = "Não foi possível encontrar o planeta informado"
        ),
        @APIResponse(
            responseCode = "500",
            description = "Erro interno da aplicação"
        )
    })
    public void delete(@PathParam("id") String id) {
        planetaService.delete(id);
    }
}