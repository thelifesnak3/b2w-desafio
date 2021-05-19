package com.github.thelifesnak3.b2w.service;

import com.github.thelifesnak3.b2w.dto.SwapiDTO;
import org.eclipse.microprofile.rest.client.ext.ResponseExceptionMapper;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.util.Optional;

@Provider
public class SwapiResponseMapper implements ResponseExceptionMapper<RuntimeException> {
    @Override
    public RuntimeException toThrowable(Response response) {
        Optional<SwapiDTO> swapiDto = Optional.ofNullable(response.readEntity(SwapiDTO.class));

        return new NotFoundException();
    }
}
