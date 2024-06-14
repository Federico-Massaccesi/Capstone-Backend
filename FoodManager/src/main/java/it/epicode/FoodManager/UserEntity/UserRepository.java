package it.epicode.FoodManager.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<UserEntity,Long>, PagingAndSortingRepository<UserEntity,Long> {


    Optional<UserEntity> findOneByUsername(String username);

    Optional<UserEntity> findByEmail(String email);

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
