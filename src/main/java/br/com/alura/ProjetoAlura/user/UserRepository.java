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
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.courses")
    List<User> findAllWithCourses();
    //RegistrationReportItem(String courseName, String courseCode, String instructorName, String instructorEmail, Long totalRegistrations)
    
    /*@Query("SELECT new br.com.alura.RegistrationReportItem(c.courseName," +
    		" c.courseCode, u.instructorName, u.instructorEmail, COUNT(r)) " +
            "FROM Course c " +
            "JOIN c.students u " +
            "LEFT JOIN Register r ON c.code = r.courseCode " +
            "WHERE c.code = :courseCode " +
            "GROUP BY c.name, c.code, u.name, u.email"
            + "ORDER BY COUNT(r) DESC")
     List<RegistrationReportItem> findCourseDetailsWithStudentCount(@Param("courseCode") String courseCode);
    */
    
    
}
