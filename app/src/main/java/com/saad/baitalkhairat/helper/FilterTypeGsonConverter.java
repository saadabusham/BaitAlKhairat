package com.saad.baitalkhairat.helper;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.saad.baitalkhairat.model.Filter;

import java.io.IOException;

public class FilterTypeGsonConverter extends TypeAdapter<Filter> {

    @Override
    public void write(JsonWriter out, Filter value) throws IOException {

        out.beginObject();
        if (value.getType() != 0) {
            out.name("type");
            out.value(value.getType());
        }

        if (!value.getCountry().isEmpty()) {
            out.name("country");
            out.value(value.getCountry());
        }

        if (!value.getCountry().isEmpty()) {
            out.name("gender");
            out.value(value.getGender());
        }
        out.endObject();
    }

    @Override
    public Filter read(JsonReader in) throws IOException {
        // do something similar, but the other way around
        return null;
    }
}