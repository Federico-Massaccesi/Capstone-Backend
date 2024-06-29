package it.epicode.FoodManager.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long>, PagingAndSortingRepository<UserEntity,Long> {


    Optional<UserEntity> findOneByUsername(String username);

    Optional<UserEntity> findByEmail(String email);

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

    @Query("SELECT u FROM UserEntity u JOIN u.roles r WHERE r.roleType IN ('COMPANY', 'PRIVATE')")
    List<UserEntity> findUsersWithCompanyOrPrivateRoles();

    @Query("SELECT u FROM UserEntity u JOIN u.roles r WHERE r.roleType IN ('COMPANY', 'PRIVATE') AND u.newsletter = true")
    List<UserEntity> findUsersForNewsletter();

    @Query("SELECT u FROM UserEntity u WHERE LOWER(u.username) LIKE LOWER(CONCAT(:query, '%'))")
    List<UserEntity> searchUsersByUsername(String query);
}
