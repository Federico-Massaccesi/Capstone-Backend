package it.epicode.FoodManager.Product;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository repository;

    @Autowired

    public Product save(Product product){
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

    public void deleteById(Long id){
        repository.deleteById(id);
    }
}
