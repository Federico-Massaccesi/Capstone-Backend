package it.epicode.FoodManager.Order;

import it.epicode.FoodManager.UserEntity.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class OrderService {


    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository clientRepository;

    public Order save(SaveOrderDTO order){
        var founded = clientRepository.findById(order.getIdClient());
        if(founded.isPresent()){
            List<CartItem> cartItems = order.getProducts().stream()
                    .map(product -> new CartItem(product.getProduct(), product.getQuantity()))
                    .toList();
            Order newOrder = Order.builder()
                    .withClient(founded.get())
                    .withItems(cartItems)
                    .withTotalPrice(order.getTotalPrice())
                    .build();
            return orderRepository.save(newOrder);
        } else {
            throw new RuntimeException("Utente non trovato");
        }
    }

    public List<Order> findAll(){
        return orderRepository.findAll();
    }

    public Order findById(Long id){
        var founded = orderRepository.findById(id);
        if(founded.isPresent()){
            return founded.get();
        } else {
            throw new RuntimeException("Ordine non trovato");
        }
    }

    public void deleteById(Long id){
        orderRepository.deleteById(id);
    }
}
