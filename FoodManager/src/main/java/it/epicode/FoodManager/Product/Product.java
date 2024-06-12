package it.epicode.FoodManager.Product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.epicode.FoodManager.BaseEntity;
import it.epicode.FoodManager.Order.Order;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "with")
@Table(name = "products")
public class Product extends BaseEntity {

    private String name;

    private List<String> categories;

    private Double price;

    private String description;

    private String imageURL;

    private Boolean available;

    @ManyToMany
    @JsonIgnore
    private List<Order> orders;
}
