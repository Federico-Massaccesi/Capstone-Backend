package it.epicode.FoodManager.Product;

import com.cloudinary.Cloudinary;
import it.epicode.FoodManager.Order.Order;
import it.epicode.FoodManager.Order.OrderService;
import it.epicode.FoodManager.Order.SaveOrderDTO;
import it.epicode.FoodManager.security.ApiValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    ProductService service;

    @Autowired
    Cloudinary cloudinary;

    @GetMapping
    public ResponseEntity<List<Product>> getAll() {

        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {

        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<?> save(@RequestPart("product") @Validated ProductValid product, @RequestPart("file") MultipartFile file, BindingResult validator) throws IOException {
        try {
            if (validator.hasErrors()) {
                throw new ApiValidationException(validator.getAllErrors());
            }
            var uploadResult = cloudinary.uploader().upload(file.getBytes(),
                    com.cloudinary.utils.ObjectUtils.asMap("public_id", product.name() + "_avatar"));

            String url = uploadResult.get("url").toString();

            var newProduct = Product.builder()
                    .withName(product.name())
                    .withAvailable(product.available())
                    .withCategories(product.categories())
                    .withDescription(product.description())
                    .withPrice(product.price())
                    .withImageURL(url)
                    .build();

            service.save(newProduct);

            return ResponseEntity.ok(newProduct);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to upload image to Cloudinary");
        }
    }

    @DeleteMapping
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }
}