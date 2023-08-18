package com.driver.Service;

import com.driver.Repository.TeacherRepository;
import com.driver.Student;
import com.driver.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.driver.Repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    public void addStudent(Student student) throws Exception{
        if(student==null) throw new Exception("Student is null Object");
        studentRepository.save(student);
    }

    public void addStudentTeacherPair(String studentName,String teacherName) throws Exception{

        if(!studentRepository.isAvailable(studentName)) throw new Exception("student is not available");
        if(!teacherRepository.isAvailable(teacherName)) throw new Exception("Teacher is not available");

        Teacher teacher=teacherRepository.getTeacher(teacherName);
        Student student=studentRepository.getStudent(studentName);

        teacher.setNumberOfStudents(teacher.getNumberOfStudents()+1);
        teacherRepository.save(teacher);

        studentRepository.getList(teacherName).add(studentName);
        teacherRepository.getList(studentName).add(teacherName);

    }

    public Student getStudentByName(String studentName){
        if(studentRepository.isAvailable(studentName)){
            return studentRepository.getStudent(studentName);
        }
        return null;
    }

    public List<String> getStudentsByTeacherName(String teacherName) throws Exception{
        if(!teacherRepository.isAvailable(teacherName)) throw new Exception("Teacher is not available");
        else{
            return studentRepository.getList(teacherName);
        }
    }

    public List<String> getAllStudents(){
        return studentRepository.getAllStudent();
    }

}
