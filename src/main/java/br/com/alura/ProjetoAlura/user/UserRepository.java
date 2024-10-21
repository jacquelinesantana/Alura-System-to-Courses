package br.com.alura.ProjetoAlura.user;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);
    Optional<User> findByEmail(@Param("email") String email);
    //for n+1 resolution
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.courses")
    List<User> findAllWithCourses();
    
}
