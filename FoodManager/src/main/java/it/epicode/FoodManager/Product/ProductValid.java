package it.epicode.FoodManager.Product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public record ProductValid(
        @NotBlank
         String name,
        @NotNull
         List<String>categories,
        @NotNull
         Double price,
        @NotNull
                @Length(max = 200)
         String description,
        @NotNull
        Boolean available
) {
}
