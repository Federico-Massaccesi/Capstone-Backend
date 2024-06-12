package it.epicode.FoodManager.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface ClientRepository extends JpaRepository<UserClient,Long>, PagingAndSortingRepository<UserClient,Long> {
}
