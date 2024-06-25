package it.epicode.FoodManager.Order;

import it.epicode.FoodManager.Product.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
class CartItemDTO {
    private Product product;
    private int quantity;
}
