package it.epicode.FoodManager.Order;

import it.epicode.FoodManager.UserEntity.ClientRepository;
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
    ClientRepository clientRepository;

    public Order save(SaveOrderDTO order){
        var founded = clientRepository.findById(order.getIdClient());
        if(founded.isPresent()){
           Order newOrder = Order.builder()
                   .withClient(founded.get())
                   .withProducts(order.getProducts())
                   .build();
           //CONTROLLARE SE GLI ATTRIBUTI VALORIZZATI PER DEFAULT VENGONO VISUALIZZATI
            return orderRepository.save(newOrder);
        }else{
            throw new RuntimeException("Utente non trovato");
        }
    }

    public List<Order> findAll(){
        return orderRepository.findAll();
    }

    public Order findById(Long id){
        var founded = orderRepository.findById(id);
        if(founded.isPresent()){
            return orderRepository.findById(id).get();
        }else{
            throw new RuntimeException("Utente non trovato");
        }
    }
//DA RAGIONARE
//    public Order modify(Long id, SaveOrderDTO order){
//
//        Order modifiedOrder = new Order();
//
//        BeanUtils.copyProperties(order, modifiedOrder);
//    }

    public void deleteById(Long id){
        orderRepository.deleteById(id);
    }
}
