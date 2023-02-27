package hypixelcryreborn.hypixelcry.utils.other;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;

import java.lang.reflect.Type;
import java.time.Instant;
import java.util.Date;

public class JsonDateDeserializer implements JsonDeserializer<Date> {
    @Override
    public Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {
        try {
            return Date.from(Instant.parse(jsonElement.getAsString()));
        }
        catch (Exception ignored) {
            return null;
        }
    }
}