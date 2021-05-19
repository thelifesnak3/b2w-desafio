package com.github.thelifesnak3.b2w.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AdicionarPlanetaDTO {

    @NotEmpty(message = "O campo nome é obrigatório")
    public String nome;
    @NotEmpty(message = "O campo clima é obrigatório")
    public String clima;
    @NotEmpty(message = "O campo terreno é obrigatório")
    public String terreno;
}
