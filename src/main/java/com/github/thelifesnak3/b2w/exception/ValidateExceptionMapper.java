package com.github.thelifesnak3.b2w.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ValidateExceptionMapper implements ExceptionMapper<ValidateException> {
    @Override
    public Response toResponse(ValidateException e) {
        ValidateError validateError = new ValidateError(
            "Ocorreu um problema na validação dos campos obrigatórios",
            e.errors);
        return Response.status(Response.Status.BAD_REQUEST).entity(validateError).build();
    }
}
