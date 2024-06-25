package it.epicode.FoodManager.Order;

import it.epicode.FoodManager.Product.Product;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "with")
@Embeddable
public class CartItem{

    @ManyToOne
    private Product product;

    private Integer quantity;
}
