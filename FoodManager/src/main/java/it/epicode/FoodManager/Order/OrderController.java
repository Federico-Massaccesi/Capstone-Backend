package it.epicode.FoodManager.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    OrderService service;

    @GetMapping
    public ResponseEntity<List<Order>> getAll() {

        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getById(@PathVariable Long id) {

        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<Order> save(@RequestBody SaveOrderDTO order) {
        return ResponseEntity.ok(service.save(order));
    }

    @DeleteMapping
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }
}
