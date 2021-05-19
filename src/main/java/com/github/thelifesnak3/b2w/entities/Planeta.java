package com.github.thelifesnak3.b2w.entities;


import io.quarkus.mongodb.panache.MongoEntity;
import org.bson.types.ObjectId;

@MongoEntity(collection="planeta")
public class Planeta {
    public ObjectId id;
    public String nome;
    public String clima;
    public String terreno;
    public Integer aparicoes;

    public Planeta() {
    }
}
