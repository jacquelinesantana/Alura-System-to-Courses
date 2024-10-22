package br.com.alura.ProjetoAlura.user;

import br.com.alura.ProjetoAlura.course.CourseRepository;
import br.com.alura.ProjetoAlura.util.ErrorItemDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {
	
	private final List<User> user = new ArrayList<>();
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    public UserController(UserRepository userRepository, CourseRepository courseRepository) {
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }

    @Transactional
    @PostMapping("/user/newStudent")
    public ResponseEntity newStudent(@RequestBody @Valid NewStudentUserDTO newStudent) {
        if(userRepository.existsByEmail(newStudent.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorItemDTO("email", "Email já cadastrado no sistema"));
        }

        User user = newStudent.toModel();
        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/user/all")
    public List<UserListItemDTO>  getAllUsers() {
        return userRepository.findAll().stream().map(UserListItemDTO::new).collect(Collectors.toList());
    }

}
