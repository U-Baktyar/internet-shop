package shop.entity.product;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class UnitEnumConverter implements AttributeConverter<UnitEnum, String> {

    @Override
    public String convertToDatabaseColumn(UnitEnum attribute) {
        return attribute != null ? attribute.getValue() : null;
    }

    @Override
    public UnitEnum convertToEntityAttribute(String dbData) {
        return dbData != null ? UnitEnum.fromValue(dbData) : null;
    }
}
