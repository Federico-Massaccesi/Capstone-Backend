package it.epicode.FoodManager.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AdminRepository extends JpaRepository<UserAdmin,Long>, PagingAndSortingRepository<UserAdmin,Long> {
}
