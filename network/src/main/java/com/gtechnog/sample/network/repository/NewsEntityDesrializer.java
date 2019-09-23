package com.gtechnog.sample.network.repository;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.gtechnog.sample.network.model.HeadlinesResponse;
import com.gtechnog.sample.network.model.MediaEntity;
import com.gtechnog.sample.network.model.NewsEntity;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

class NewsEntityDesrializer implements JsonDeserializer<NewsEntity> {

    @Override
    public NewsEntity deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        JsonObject jsonObject = json.getAsJsonObject();
        Gson gson = new Gson();
        NewsEntity newsEntity = gson.fromJson(jsonObject, NewsEntity.class);
        if (jsonObject.get("multimedia").isJsonArray()) {
            // parse this whole media list as a array list and assign to this news entity
            JsonArray multimedia = jsonObject.get("multimedia").getAsJsonArray();
            Type type = new TypeToken<ArrayList<MediaEntity>>(){}.getType();
            newsEntity.setMediaList((List<MediaEntity>) gson.fromJson(multimedia, type));
        }
        return newsEntity;

    }
}
