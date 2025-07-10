package com.github.redfox197;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.Document;
import org.bson.codecs.pojo.annotations.BsonExtraElements;

import java.util.Map;

@MongoEntity(collection = "configuration")
public class Configuration extends PanacheMongoEntity {
    public String name;

    public Map<String, Object> fields;

    @BsonExtraElements
    public Document extra;
}