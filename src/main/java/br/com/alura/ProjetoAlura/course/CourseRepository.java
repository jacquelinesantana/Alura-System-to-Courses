package br.com.alura.ProjetoAlura.course;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>{

	boolean existsByCode(String code);
	public Optional<Course> findByCode(@Param("code") String code);

	//for n+1 resolution
    @Query("SELECT u FROM Course u LEFT JOIN FETCH u.students")
    List<Course> findAllWithStudents();
    
    @Query("SELECT c FROM Course c WHERE c.statusCourse = 'ACTIVE' AND c.code = :code")
    public Optional<Course> findByCodeAndActiveCourses(@Param("code") String code);
}
