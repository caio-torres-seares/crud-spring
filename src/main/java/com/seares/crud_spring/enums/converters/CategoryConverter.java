package com.seares.crud_spring.enums.converters;

import java.util.stream.Stream;

import com.seares.crud_spring.enums.Category;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class CategoryConverter implements AttributeConverter<Category, String> {

    @Override
    public String convertToDatabaseColumn(Category category) {
        if (category == null) {
            return null;
        }
        return category.getCategory();
    }

    @Override
    public Category convertToEntityAttribute(String value) {
        if (value == null) {
            return null;
        }
        return Stream.of(Category.values())
                .filter(category -> category.getCategory().equals(value))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
    
}
