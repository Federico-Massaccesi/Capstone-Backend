package it.epicode.FoodManager.Order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveOrderDTO {
    private Long clientId;
    private List<CartItemDTO> products;
    private Double totalPrice;
    private Boolean pending = false;
}


