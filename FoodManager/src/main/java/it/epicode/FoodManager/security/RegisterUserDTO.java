package it.epicode.FoodManager.security;

import it.epicode.FoodManager.UserEntity.Roles;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class RegisterUserDTO {
    String username;
    String email;
    String password;
    String companyName;
    String pIVA;
    String address;
    String town;
    List<Roles> roles;
    Boolean newsletter;
}