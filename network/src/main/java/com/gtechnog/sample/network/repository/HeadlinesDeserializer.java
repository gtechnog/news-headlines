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

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

class HeadlinesDeserializer implements JsonDeserializer<HeadlinesResponse> {

    @Override
    public HeadlinesResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        Gson gson = new Gson();
        HeadlinesResponse headlinesResponse = gson.fromJson(jsonObject, HeadlinesResponse.class);
        JsonArray jsonArray = jsonObject.get("results").getAsJsonArray();
        if (jsonArray != null && jsonArray.size() != 0) {
            for (int i = 0 ; i < jsonArray.size() ; i++) {
                JsonObject object = jsonArray.get(i).getAsJsonObject();
                if (object.get("multimedia").isJsonArray()) {
                    // parse this whole media list as a array list and assign to this news entity
                    Type type = new TypeToken<ArrayList<MediaEntity>>(){}.getType();
                    headlinesResponse.getNewsEntities().get(i).setMediaList((List<MediaEntity>) gson.fromJson(object, type));
                }
            }
        }
        return headlinesResponse;
    }
}
