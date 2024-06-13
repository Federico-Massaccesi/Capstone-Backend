package it.epicode.FoodManager.UserEntity;

import it.epicode.FoodManager.Order.Order;
import it.epicode.FoodManager.Order.OrderRepository;
import it.epicode.FoodManager.Order.SaveOrderDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    public List<UserClient> findAll(){
        return clientRepository.findAll();
    }

    public UserClient findById(Long id){
        var founded = clientRepository.findById(id);
        if(founded.isPresent()){
            return clientRepository.findById(id).get();
        }else{
            throw new RuntimeException("Utente non trovato");
        }
    }
   public UserClient modify(Long id, UserClient order){

        var founded = clientRepository.findById(id);
        if(founded.isPresent()){
            UserClient modifiedUser = new UserClient();

            BeanUtils.copyProperties(order, modifiedUser);

            return clientRepository.save(modifiedUser);
        }else{
            throw new RuntimeException("Utente non trovato");
        }
   }
    public void deleteById(Long id){
        clientRepository.deleteById(id);
    }
}
