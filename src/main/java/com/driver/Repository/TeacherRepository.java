package com.driver.Repository;

import com.driver.Student;
import com.driver.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TeacherRepository {

    @Autowired
    private StudentRepository studentRepository;

    Map<String, Teacher> teacherMap=new HashMap<>();
    Map<String, List<String>>teacherListMap=new HashMap<>();

    public void save(Teacher teacher){
        teacherMap.put(teacher.getName(),teacher);
    }

    public Teacher getTeacher(String name){
        if(teacherMap.containsKey(name)) return teacherMap.get(name);
        return null;
    }

    public List<String> getAllTeacher(){
        List<String>teacherList=new ArrayList<>();
        teacherList.addAll(teacherMap.keySet());
        return teacherList;
    }

    public void delete(String name){
        teacherMap.remove(name);
        studentRepository.getList(name).remove(name);
        for(String studentName : teacherListMap.keySet()){
            if(teacherListMap.get(studentName).contains(name)){
                teacherListMap.get(studentName).remove(name);
            }
        }
    }

    public void deleteAll(){
        teacherMap.clear();
        teacherListMap.clear();
    }

    public boolean isAvailable(String name){
        return teacherMap.containsKey(name);
    }

    public List<String> getList(String name){
        if(teacherListMap.containsKey(name)) return teacherListMap.get(name);
        return new ArrayList<>();
    }
}
