package shop.serializeble;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;


import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataDeserializer extends JsonDeserializer<LocalDate> {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @Override
    public LocalDate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {

        String dateAsString = jsonParser.getText();
        if (dateAsString == null) {
            return null;
        }

        return LocalDate.parse(dateAsString, formatter);
    }
}
