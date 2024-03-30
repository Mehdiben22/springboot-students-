package com.springbootpr.springstudent.service;

import com.springbootpr.springstudent.model.Student;

import java.util.List;

public interface IStudentService {
    Student addStudent(Student student);

    //Get all the students
    List<Student> getStudents();

    Student updateStudent(Student student , Long id );

    //get a single student
    Student getStudentById(Long id);

    void deleteStudent(Long id );

}
