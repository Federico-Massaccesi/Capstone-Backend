package it.epicode.FoodManager.Product;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository repository;

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
}
