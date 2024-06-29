package it.epicode.FoodManager.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    OrderService service;

    @GetMapping
    public ResponseEntity<List<Order>> getAll() {

        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{userId}/orders")
    public List<Order> getUserOrders(@PathVariable Long userId) {
        return service.findOrdersByUserId(userId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getById(@PathVariable Long id) {

        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<Order> createOrder(@RequestBody SaveOrderDTO order) {
        Order savedOrder = service.save(order);
        return ResponseEntity.ok(savedOrder);
    }

    @PatchMapping("/{id}/checked")
    public ResponseEntity<Order> updateOrderChecked(@PathVariable Long id, @RequestBody Map<String, Boolean> update) {
        Boolean checked = update.get("checked");
        if (checked == null) {
            return ResponseEntity.badRequest().build();
        }
        try {
            Order updatedOrder = service.updateOrderChecked(id, checked);
            return ResponseEntity.ok(updatedOrder);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }

    @PatchMapping("/{id}/completed")
    public ResponseEntity<Order> updateOrderPending(@PathVariable Long id, @RequestBody Map<String, Boolean> updates) {
        Boolean pending = updates.get("pending");
        Order updatedOrder = service.updateOrderPending(id, pending);
        return ResponseEntity.ok(updatedOrder);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }
}
