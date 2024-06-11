package it.epicode.FoodManager.Order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface Repository extends JpaRepository<Order,Long>, PagingAndSortingRepository<Order,Long> {
}
