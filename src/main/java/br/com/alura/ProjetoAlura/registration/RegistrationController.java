package br.com.alura.ProjetoAlura.registration;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.ProjetoAlura.course.Course;
import br.com.alura.ProjetoAlura.course.CourseRepository;
import br.com.alura.ProjetoAlura.user.User;
import br.com.alura.ProjetoAlura.user.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class RegistrationController {
	private final UserRepository userRepository;
	private final CourseRepository courseRepository;
	
	public RegistrationController(UserRepository userRepository, CourseRepository courseRepository) {
		this.userRepository = userRepository;
		this.courseRepository = courseRepository;
	}

    @PostMapping("/registration/new")
    public ResponseEntity createCourse(@Valid @RequestBody NewRegistrationDTO newRegistration) {
        Optional<User> user = userRepository.findByEmail(newRegistration.getStudentEmail());
        Optional<Course> course = courseRepository.findByCode(newRegistration.getCourseCode());
        
        if(user.isPresent() && course.isPresent()) {
        	Course courseX = course.get();
        	User userX = user.get();
        	
        	if(userX.getCourses().contains(courseX)) {
        		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error, This student already belongs to this course.");
        	}
        	
        	userX.getCourses().add(courseX);
        	
        	userRepository.save(userX);
        	 
        	return ResponseEntity.status(HttpStatus.CREATED).build();
        	
        }
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: Course or Student not exists");
        
    }
    
    /*
     * @PostMapping("/add")
    public ResponseEntity<String> enrollStudent(@RequestBody EnrollRequestDTO enrollRequest) {
        Optional<User> userOpt = userRepository.findByEmail(enrollRequest.getEmailUser());
        Optional<Course> courseOpt = courseRepository.findByCode(enrollRequest.getCodeCourse());

        if (userOpt.isPresent() && courseOpt.isPresent()) {
            User user = userOpt.get();
            Course course = courseOpt.get();
            
            // Adiciona o curso na lista de cursos do aluno
            user.getCourses().add(course);
            
            // Salva a relação
            userRepository.save(user);

            return ResponseEntity.ok("Student enrolled successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User or Course not found");
        }
    }
     */

    @GetMapping("/registration/report")
    public ResponseEntity<List<RegistrationReportItem>> report() {
        List<RegistrationReportItem> items = new ArrayList<>();

        // TODO: Implementar a Questão 4 - Relatório de Cursos Mais Acessados aqui...

        // Dados fictícios abaixo que devem ser substituídos
        items.add(new RegistrationReportItem(
                "Java para Iniciantes",
                "java",
                "Charles",
                "charles@alura.com.br",
                10L
        ));

        items.add(new RegistrationReportItem(
                "Spring para Iniciantes",
                "spring",
                "Charles",
                "charles@alura.com.br",
                9L
        ));

        items.add(new RegistrationReportItem(
                "Maven para Avançados",
                "maven",
                "Charles",
                "charles@alura.com.br",
                9L
        ));

        return ResponseEntity.ok(items);
    }

}
