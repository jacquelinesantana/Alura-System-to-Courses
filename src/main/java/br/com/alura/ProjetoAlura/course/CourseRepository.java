package br.com.alura.ProjetoAlura.course;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>{

	boolean existsByCode(String code);
	public Optional<Course> findByCode(@Param("code") String code);

}
