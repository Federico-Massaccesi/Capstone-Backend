package it.epicode.FoodManager.UserEntity;

import it.epicode.FoodManager.security.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200")

public class UserController {

    @Autowired
    private UserService user;

    @GetMapping
    public ResponseEntity<List<UserEntity>> getAll() {

        return ResponseEntity.ok(user.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getById(@PathVariable Long id) {

        return ResponseEntity.ok(user.findById(id));
    }

    @PostMapping("/register")
    public ResponseEntity<RegisteredUserDTO> register(@RequestBody @Validated RegisterUserModel model, BindingResult validator){
        if (validator.hasErrors()) {
            throw new ApiValidationException(validator.getAllErrors());
        }
try {
    var registeredUser = user.register(
            RegisterUserDTO.builder()
                    .withUsername(model.username())
                    .withEmail(model.email())
                    .withPassword(model.password())
                    .withAddress(model.address())
                    .withCity(model.city())
                    .withCompanyName(model.companyName())
                    .withPIVA(model.pIVA())
                    .withRoles(model.roles())
                    .build());

    return  new ResponseEntity<> (registeredUser, HttpStatus.OK);
}catch (Exception e){
    throw new RuntimeException("Errore durante la registrazione");
}

    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Validated LoginModel model, BindingResult validator) {
        if (validator.hasErrors()) {
            throw  new ApiValidationException(validator.getAllErrors());
        }
        return new ResponseEntity<>(user.login(model.username(), model.password()).orElseThrow(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        user.deleteById(id);
    }
}
