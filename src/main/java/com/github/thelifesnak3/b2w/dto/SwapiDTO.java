package com.github.thelifesnak3.b2w.dto;

import java.util.List;

public class SwapiDTO {
    public Integer count;
    public List<SwapiPlanetaDTO> results;

    public Integer getAparicoes() {
        return (results != null && results.size() > 0) ?
                results.get(0).films.size() :
                0;
    }
}
