package com.driver.Service;

import com.driver.Student;
import com.driver.Teacher;
import com.driver.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    public StudentRepository studentRepository;

    public void addStudent(Student student) throws Exception{
        //validation
        if(student==null) throw new Exception("Student is null");
        studentRepository.save(student);

    }

    public void addTeacher(Teacher teacher) throws Exception {
        if(teacher==null) throw new Exception("teacher is null");
        studentRepository.save(teacher);
    }

    public void addStudentTeacherPair(String studentName, String teacherName) throws Exception{
        Student student=studentRepository.getStudent(studentName);
        Teacher teacher=studentRepository.getTeacher(teacherName);
        if(student==null) throw new Exception("Student is not present in Db");
        if(teacher==null) throw new Exception("Teacher is not present in Db");
        studentRepository.pair(studentName,teacherName);
    }

    public Student getStudentByName(String studentName){
        return studentRepository.getStudent(studentName);
    }

    public Teacher getTeacherByName(String teacherName){
        return studentRepository.getTeacher(teacherName);
    }

    public List<String> getStudentsByTeacherName(String teacherName){
        Teacher teacher=studentRepository.getTeacher(teacherName);
        return studentRepository.getList(teacher);
    }

    public List<String>getAllStudents(){
        return studentRepository.getAllStudent();
    }

    public void deleteTeacher(String teacherName) throws Exception{
        Teacher teacher=studentRepository.getTeacher(teacherName);
        //validation
        if(teacher==null) throw new Exception("Teacher is not preesent in Db");
        //deletion
        studentRepository.delete(teacher);
    }
    
}
