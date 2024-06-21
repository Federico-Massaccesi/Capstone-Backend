package it.epicode.FoodManager.Product;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    CategoryService svc;

    @GetMapping
    public ResponseEntity<List<Category>> getAll() {

        return ResponseEntity.ok(svc.findAll());
    }

    @PostMapping
    public ResponseEntity<Category> save(@RequestBody  CategoryDTO category){

        Category newCat = new Category();

        BeanUtils.copyProperties(category, newCat);

        return ResponseEntity.ok(svc.save(newCat));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getAll(@PathVariable Long id) {

        return ResponseEntity.ok(svc.findById(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {

         svc.deleteById(id);
    }
}
