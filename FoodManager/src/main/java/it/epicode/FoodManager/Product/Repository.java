package it.epicode.FoodManager.Product;

import it.epicode.FoodManager.Order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface Repository extends JpaRepository<Product,Long>, PagingAndSortingRepository<Product,Long> {
}
