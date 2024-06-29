package it.epicode.FoodManager.UserEntity;

import it.epicode.FoodManager.Product.Product;
import it.epicode.FoodManager.security.*;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    RolesRepository rolesRepository;
    @Autowired
    AuthenticationManager auth;
    @Autowired
    JwtUtils jwt;


    public Optional<LoginResponseDTO> login(String username, String password) {
        try {

            var a = auth.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            a.getAuthorities();

            SecurityContextHolder.getContext().setAuthentication(a);

            var user = userRepository.findOneByUsername(username).orElseThrow();
            var dto = LoginResponseDTO.builder()
                    .withUser(RegisteredUserDTO.builder()
                            .withId(user.getId())
                            .withEmail(user.getEmail())
                            .withRoles(user.getRoles())
                            .withUsername(user.getUsername())
                            .build())
                    .build();


            dto.setToken(jwt.generateToken(a));

            return Optional.of(dto);
        } catch (NoSuchElementException | AuthenticationException e) {
            throw new InvalidLoginException(username, password);
        }
    }

    public RegisteredUserDTO register(RegisterUserDTO register){
        if(userRepository.existsByUsername(register.getUsername())){
            throw new EntityExistsException("Utente gia' esistente");
        }
//        if(userRepository.existsByEmail(register.getEmail())){
//            throw new EntityExistsException("Email gia' registrata");
//        }
        Roles roles = rolesRepository.findById(register.getRoles().get(0).getRoleType())
                .orElseThrow(() -> new IllegalArgumentException("Ruolo non valido"));

        UserEntity u = new UserEntity();
        BeanUtils.copyProperties(register, u);
        u.setPassword(encoder.encode(register.getPassword()));
        u.getRoles().add(roles);
        userRepository.save(u);
        RegisteredUserDTO response = new RegisteredUserDTO();
        BeanUtils.copyProperties(u, response);
        response.setRoles(List.of(roles));
        return response;

    }


    public List<UserEntity> findAll(){
        return userRepository.findAll();
    }

    public UserEntity findById(Long id){
        var founded = userRepository.findById(id);
        if(founded.isPresent()){
            return userRepository.findById(id).get();
        }else{
            throw new RuntimeException("Utente non trovato");
        }
    }
   public UserEntity modify(Long id, UserEntity order){

        var founded = userRepository.findById(id);
        if(founded.isPresent()){
            UserEntity modifiedUser = new UserEntity();

            BeanUtils.copyProperties(order, modifiedUser);

            return userRepository.save(modifiedUser);
        }else{
            throw new RuntimeException("Utente non trovato");
        }
   }
    public void deleteById(Long id){
        userRepository.deleteById(id);
    }

    public List<UserEntity> getUsersWithCompanyOrPrivateRoles() {
        return userRepository.findUsersWithCompanyOrPrivateRoles();
    }

    public List<UserEntity> getNewsletterUsers() {
        return userRepository.findUsersForNewsletter();
    }

    public List<UserEntity> searchUsersByUsername(String query) {
        return userRepository.searchUsersByUsername(query);
    }
}
