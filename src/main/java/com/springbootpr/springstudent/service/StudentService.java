package com.springbootpr.springstudent.service;

import com.springbootpr.springstudent.exception.StudentAlreadyExistsException;
import com.springbootpr.springstudent.exception.StudentNotFoundException;
import com.springbootpr.springstudent.model.Student;
import com.springbootpr.springstudent.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
//Nous permet d'injecter the repository we created to our implementation
@RequiredArgsConstructor
//implementing the services we created
public class StudentService implements IStudentService{
    //giving this a reference by studentrepository we created
    private final StudentRepository studentRepository;
    @Override
    public Student addStudent(Student student) {
        if(studentAlreadyExists(student.getEmail())) {
            throw new StudentAlreadyExistsException(student.getEmail() + "Already exists");
        }
        return studentRepository.save(student);
    }

    @Override
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student updateStudent(Student student, Long id) {
        return studentRepository.findById(id).map(st -> {
            st.setFirstname(student.getFirstname());
            st.setLastname(student.getLastname());
            st.setEmail(student.getEmail());
            st.setDepartment(student.getDepartment());
            return studentRepository.save(st);
        }).orElseThrow(()-> new StudentNotFoundException("Sorry , this student could not be found"));

    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Sorry , this student could not be found :"+id));
    }

    @Override
    public void deleteStudent(Long id) {
        if(!studentRepository.existsById(id)) {
            throw new StudentNotFoundException("Sorry , student not found");
        }
        studentRepository.deleteById(id);
    }
    private boolean studentAlreadyExists(String email) {
        return studentRepository.findByEmail(email).isPresent();
    }
}


