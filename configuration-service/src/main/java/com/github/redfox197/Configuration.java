package com.github.redfox197;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.Document;
import org.bson.codecs.pojo.annotations.BsonExtraElements;

@MongoEntity(database = "configurations", collection = "configuration")
public class Configuration extends PanacheMongoEntity {
    public String name;
    public int pippo;

    // FIXME credo di aver capito che funziona solo in lettura da Mongodb
    @BsonExtraElements
    public Document extra;
}
