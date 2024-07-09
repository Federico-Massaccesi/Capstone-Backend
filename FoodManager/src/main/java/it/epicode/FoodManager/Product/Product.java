package it.epicode.FoodManager.Product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.epicode.FoodManager.BaseEntity;
import it.epicode.FoodManager.Order.Order;
import jakarta.persistence.*;
import lombok.*;

import java.io.File;
import java.util.ArrayList;
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

    @ManyToMany
    @JoinTable(
            name = "products_categories",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "categories_id")
    )
    private List<Category> categories;

    private Double price;

    private String description;

    private String imageURL;

    private Boolean available;

    @ManyToMany
    @JsonIgnore
    private List<Order> orders = new ArrayList<>();

}
