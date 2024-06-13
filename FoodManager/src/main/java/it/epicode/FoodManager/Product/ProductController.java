package it.epicode.FoodManager.Product;

import it.epicode.FoodManager.Order.Order;
import it.epicode.FoodManager.Order.OrderService;
import it.epicode.FoodManager.Order.SaveOrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    ProductService service;

    @GetMapping
    public ResponseEntity<List<Product>> getAll() {

        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id) {

        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<Product> save(@RequestBody Product order) {
        return ResponseEntity.ok(service.save(order));
    }

    @DeleteMapping
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }
}
