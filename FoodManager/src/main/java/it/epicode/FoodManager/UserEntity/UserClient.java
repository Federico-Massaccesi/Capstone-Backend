package it.epicode.FoodManager.UserEntity;

import it.epicode.FoodManager.BaseEntity;
import it.epicode.FoodManager.Order.Order;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "users")
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
public class UserClient extends BaseEntity {

    private String username;

    private String password;

    private String companyName;

    @OneToMany(mappedBy = "client")
    private List<Order> orders;
    @OneToMany(mappedBy = "client")
    private List<Order> pendingOrders;

    private String pIVA;

    private String address;

    private String city;
}
