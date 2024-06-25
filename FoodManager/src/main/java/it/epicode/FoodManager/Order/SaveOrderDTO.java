package it.epicode.FoodManager.Order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveOrderDTO {
    private Long idClient;
    private List<CartItemDTO> products;
    private Double totalPrice;
}


