package br.com.alura.ProjetoAlura.user;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import br.com.alura.ProjetoAlura.registration.RegistrationReportItem;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);
    Optional<User> findByEmail(@Param("email") String email);
    
    //for n+1 resolution
    @Query("SELECT u FROM User u ")
    List<RegistrationReportItem> findAllWithCourses();
    
    @Query("SELECT new br.com.alura.ProjetoAlura.registration.RegistrationReportItem(c.name, c.code, "
    		+ "(SELECT u.name FROM User u WHERE u.email = c.instructorEmail) AS name , "
    		+ "c.instructorEmail, COUNT(u)) " +
    	       "FROM Course c " +
    	       "JOIN c.students u " +
    	       
    	       "GROUP BY c.name ORDER BY Count(u) DESC")
    	List<RegistrationReportItem> findCourseDetailsWithStudentCount();
 
}
