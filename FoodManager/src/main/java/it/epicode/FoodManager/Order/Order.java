package it.epicode.FoodManager.Order;

import it.epicode.FoodManager.BaseEntity;
import it.epicode.FoodManager.Product.Product;
import it.epicode.FoodManager.UserEntity.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
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
    private UserEntity client;

    @ManyToMany
    private List<Product> products = new ArrayList<>();

    private LocalDate localDate = LocalDate.now();

    private Boolean pending = true;

    private Double totalPrice = getTotalPrice();

    public Double getTotalPrice(){

        double totalPrice = 0.0;

        for (Product product : this.products) {
            totalPrice += product.getPrice();
        }
        return totalPrice;
    }
}
