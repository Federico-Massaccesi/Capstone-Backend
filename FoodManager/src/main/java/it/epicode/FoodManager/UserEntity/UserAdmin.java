package it.epicode.FoodManager.UserEntity;

import it.epicode.FoodManager.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "with")
@Table(name = "admins")
public class UserAdmin extends BaseEntity {

    private String username;

    private String password;

    private String role;



}
