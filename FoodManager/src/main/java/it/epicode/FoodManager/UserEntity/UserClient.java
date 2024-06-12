package it.epicode.FoodManager.UserEntity;

import it.epicode.FoodManager.BaseEntity;
import it.epicode.FoodManager.Order.Order;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "users")
@Builder(setterPrefix = "with")
public class UserClient extends BaseEntity {

    private String username;

    private String password;

    @OneToMany(mappedBy = "client")
    private List<Order> orders;

}
