package it.epicode.FoodManager.Order;

import it.epicode.FoodManager.Product.Product;
import lombok.Data;


import java.util.List;
@Data
public class SaveOrderDTO {

    private Long idClient;

    private List<Product> products;
}
