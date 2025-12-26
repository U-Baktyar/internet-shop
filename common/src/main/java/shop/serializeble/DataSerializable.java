package shop.serializeble;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDate;

public class DataSerializable extends JsonSerializer<LocalDate> {

    @Override
    public void serialize(LocalDate date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (date != null) {
            String formatted = String.format("%02d-%02d-%04d",
                    date.getDayOfMonth(),
                    date.getMonthValue(),
                    date.getYear());
            jsonGenerator.writeString(formatted);
        } else {
            jsonGenerator.writeNull();
        }
    }
}
