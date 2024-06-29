package it.epicode.FoodManager.Order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long>, PagingAndSortingRepository<Order,Long> {
    @Query("SELECT o FROM Order o WHERE o.client.id = :userId")
    List<Order> findByClientId(@Param("userId") Long userId);
}
