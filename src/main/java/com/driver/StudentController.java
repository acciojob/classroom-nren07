package com.driver;

import java.util.List;

import com.driver.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("students")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @PostMapping("/add-student")
    public ResponseEntity<String> addStudent(@RequestBody Student student){
        try{
            studentService.addStudent(student);
            return new ResponseEntity<>("New student added successfully", HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }


    }

    @PostMapping("/add-teacher")
    public ResponseEntity<String> addTeacher(@RequestBody Teacher teacher){
        try{
            studentService.addTeacher(teacher);
            return new ResponseEntity<>("New teacher added successfully", HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/add-student-teacher-pair")
    public ResponseEntity<String> addStudentTeacherPair(@RequestParam String student, @RequestParam String teacher){
        try{
            studentService.addStudentTeacherPair(student,teacher);
            return new ResponseEntity<>("New student-teacher pair added successfully", HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/get-student-by-name/{name}")
    public ResponseEntity<Student> getStudentByName(@PathVariable String name){
        Student student = null; // Assign student by calling service layer method
        student=studentService.getStudentByName(name);
        if(student==null) return new ResponseEntity<>(null,HttpStatus.EXPECTATION_FAILED);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @GetMapping("/get-teacher-by-name/{name}")
    public ResponseEntity<Teacher> getTeacherByName(@PathVariable String name){
        Teacher teacher = null; // Assign student by calling service layer method
        teacher=studentService.getTeacherByName(name);
        if(teacher==null) return new ResponseEntity<>(null,HttpStatus.EXPECTATION_FAILED);
        return new ResponseEntity<>(teacher, HttpStatus.CREATED);
    }

    @GetMapping("/get-students-by-teacher-name/{teacher}")
    public ResponseEntity<List<String>> getStudentsByTeacherName(@PathVariable String teacher){
        List<String> students = null; // Assign list of student by calling service layer method
        students=studentService.getStudentsByTeacherName(teacher);
        return new ResponseEntity<>(students, HttpStatus.CREATED);
    }

    @GetMapping("/get-all-students")
    public ResponseEntity<List<String>> getAllStudents(){
        List<String> students = null; // Assign list of student by calling service layer method
        students=studentService.getAllStudents();
        return new ResponseEntity<>(students, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete-teacher-by-name")
    public ResponseEntity<String> deleteTeacherByName(@RequestParam String teacher){
        try{
            studentService.deleteTeacher(teacher);
            return new ResponseEntity<>(teacher + " removed successfully", HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NO_CONTENT);
        }

    }
    @DeleteMapping("/delete-all-teachers")
    public ResponseEntity<String> deleteAllTeachers(){

        return new ResponseEntity<>("All teachers deleted successfully", HttpStatus.CREATED);
    }
}
