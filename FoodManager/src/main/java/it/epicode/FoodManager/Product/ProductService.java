package it.epicode.FoodManager.Product;

import com.cloudinary.Cloudinary;
import it.epicode.FoodManager.UserEntity.UserEntity;
import it.epicode.FoodManager.UserEntity.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository repository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private UserService userService;

    public Product save(Product product){

        sendNotificationNewProduct(product);
        return repository.save(product);
    }

    public List<Product> findAll(){
        return repository.findAll();
    }

    public Product findById(Long id){
        var founded = repository.findById(id);
        if(founded.isPresent()){
            return repository.findById(id).get();
        }else{
            throw new RuntimeException("Prodotto non trovato");
        }
    }
    public Product modify(Long id, Product product){

        var founded = repository.findById(id);

        if(founded.isPresent()){

            Product modifiedProduct = new Product();

            BeanUtils.copyProperties(product, modifiedProduct);

            return repository.save(modifiedProduct);
        }else{
            throw new RuntimeException("Prodotto non trovato");
        }
    }

    @Transactional
    public Product updateProductAvailability(Long productId, boolean available) {
        Product product = repository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + productId));

        product.setAvailable(available);

        return repository.save(product);
    }

    @Transactional
    public Product updateProduct(Long productId, Product updatedProduct) {
        Product product = repository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + productId));

        product.setName(updatedProduct.getName());
        product.setDescription(updatedProduct.getDescription());
        product.setPrice(updatedProduct.getPrice());
        product.setAvailable(updatedProduct.getAvailable());

        return repository.save(product);
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }

    public void sendNotificationNewProduct(Product product) {
        List<UserEntity> users = userService.getUsersWithCompanyOrPrivateRoles();
        System.out.println(users);
        for (UserEntity user : users) {
            sendEmail(user.getEmail(), product);
        }
    }

    private void sendEmail(String email, Product product) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("New product notification");
        message.setText("Ciao! Food Manager ti avvisa che è disponibile un nuovo prodotto!" +
                "Vai sul nostro sito sulla sezione prodotti e cerca :" + product.getName() + "!");
        javaMailSender.send(message);
    }
}
