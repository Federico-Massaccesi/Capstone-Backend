package it.epicode.FoodManager.Product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public record ProductValidPut(
        String name,
        List<Long> categories,
        Double price,
        String description,
        Boolean available
) {

}
