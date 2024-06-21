package it.epicode.FoodManager.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository repo;

    public Category save(Category product){
        return repo.save(product);
    }

    public List<Category> findAll(){
        return repo.findAll();
    }

    public Category findById(Long id){
        var founded = repo.findById(id);
        if(founded.isPresent()){
            return repo.findById(id).get();
        }else{
            throw new RuntimeException("Categoria non trovato");
        }
    }


    public void deleteById(Long id){
        repo.deleteById(id);
    }

}
