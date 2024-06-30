package it.epicode.FoodManager.Order;

import it.epicode.FoodManager.BaseEntity;
import it.epicode.FoodManager.UserEntity.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private UserEntity client;

    @ElementCollection
    @CollectionTable(name = "order_items", joinColumns = @JoinColumn(name = "order_id"))
    private List<CartItem> items  = new ArrayList<>();

    @Builder.Default
    private LocalDateTime localDate = LocalDateTime.now();

    private Boolean pending;
    @Builder.Default
    private Boolean checked = false;
    @Builder.Default
    private Boolean completed = false;

    private Double totalPrice;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + getId() +
                ", clientId=" + (client != null ? client.getId() : null) +
                ", localDate=" + localDate +
                ", pending=" + pending +
                ", checked=" + checked +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
