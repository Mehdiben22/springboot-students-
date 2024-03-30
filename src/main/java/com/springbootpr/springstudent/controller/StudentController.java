package com.springbootpr.springstudent.controller;


import com.springbootpr.springstudent.model.Student;
import com.springbootpr.springstudent.service.IStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//Giving permission to our front end for fetch the data
@CrossOrigin({"http://localhost:3000", "https://studentslist-mehdi.vercel.app"})

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {
    //constructor injection here
    private final IStudentService studentService;

    //Get the students List
  @GetMapping
    public ResponseEntity<List<Student>> getStudents() {
      return new ResponseEntity<>(studentService.getStudents(), HttpStatus.FOUND);
  }

    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }
   @PutMapping("/update/{id}")
   //The pathvariable is important to modify the student by his id
    public Student updateStudent(@RequestBody Student student, @PathVariable Long id) {
      return studentService.updateStudent(student, id);

    }
    @DeleteMapping("/delete/{id}")
    public void deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
    }
  @GetMapping("/student/{id}")
    public Student getStudentByID(@PathVariable Long id) {
      return studentService.getStudentById(id);
    }

}
