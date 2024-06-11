package it.epicode.FoodManager.Order;

import it.epicode.FoodManager.BaseEntity;
import it.epicode.FoodManager.Product.Product;
import it.epicode.FoodManager.UserEntity.UserClient;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "with")
@Table(name = "orders")
public class Order extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "client_id")
    private UserClient client;

    @ManyToMany
    private List<Product> products;

    public Double getTotalPrice(){

        double totalPrice = 0.0;

        for (Product product : this.products) {
            totalPrice += product.getPrice();
        }
        return totalPrice;
    }
}
