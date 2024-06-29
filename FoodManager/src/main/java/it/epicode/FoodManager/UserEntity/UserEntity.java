package it.epicode.FoodManager.UserEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.epicode.FoodManager.BaseEntity;
import it.epicode.FoodManager.Order.Order;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "users")
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity extends BaseEntity {

    private String username;

    private String password;

    private String companyName;
    @JsonIgnore
    @OneToMany(mappedBy = "client")
    private List<Order> orders;

    private String email;

    private String pIVA;

    private String address;

    private String city;

    @ManyToMany(fetch = FetchType.EAGER)
    private final List<Roles> roles = new ArrayList<>();

    private Boolean newsletter;

    @Override
    public String toString() {
        return "UserEntity{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", pIVA='" + pIVA + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                '}';
    }

}
