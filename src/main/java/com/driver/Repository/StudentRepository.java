package com.driver.Repository;

import com.driver.Student;
import com.driver.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StudentRepository {

    @Autowired
    private TeacherRepository teacherRepository;
    Map<String, Student> studentMap=new HashMap<>();
    Map<String, List<String>>studentListMap=new HashMap<>();

    public void save(Student student){
        studentMap.put(student.getName(),student);
    }

    public boolean isAvailable(String studentName){
        return studentMap.containsKey(studentName);
    }


    public Student getStudent(String name){
        if(studentMap.containsKey(name)) return studentMap.get(name);
        return null;
    }


    public List<String> getList(String name){
        if(studentListMap.containsKey(name)) return studentListMap.get(name);
        return new ArrayList<>();
    }


    public List<String> getAllStudent(){
        List<String>studentList=new ArrayList<>();
        studentList.addAll(studentMap.keySet());
        return studentList;
    }

    public void deleteAllTeacher(){
        studentListMap.clear();
    }

}
