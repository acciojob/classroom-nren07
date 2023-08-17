package com.driver.Repository;

import com.driver.Student;
import com.driver.Teacher;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StudentRepository {

    Map<String, Student> studentMap=new HashMap<>();
    Map<String, Teacher>teacherMap=new HashMap<>();

    Map<String, List<String>>stList=new HashMap<>();

    Map<String, List<String>>teachList=new HashMap<>();

    public void save(Student student){
        studentMap.put(student.getName(),student);
    }
    public void save(Teacher teacher){
        teacherMap.put(teacher.getName(),teacher);
    }

    public Student getStudent(String name){
        if(studentMap.containsKey(name)) return studentMap.get(name);
        return null;
    }
    public Teacher getTeacher(String name){
        if(teacherMap.containsKey(name)) return teacherMap.get(name);
        return null;
    }

    public void pair(String studentName,String teacherName){
        Student student=studentMap.getOrDefault(studentName,null);
        Teacher teacher=teacherMap.getOrDefault(teacherName,null);

        teacher.setNumberOfStudents(teacher.getNumberOfStudents()+1);

        stList.getOrDefault(teacherName,new ArrayList<>()).add(student.getName());
        teachList.getOrDefault(studentName,new ArrayList<>()).add(teacher.getName());

    }

    public List<String> getList(Teacher teacher){
        if(teacher!=null && stList.containsKey(teacher.getName())) return stList.get(teacher.getName());
        return new ArrayList<>();
    }
    public List<String> getList(Student student){
        if(student!=null && teachList.containsKey(student.getName())) return teachList.get(student.getName());
        return new ArrayList<>();
    }

    public List<String> getAllStudent(){
        List<String>studentList=new ArrayList<>();
        studentList.addAll(studentMap.keySet());
        return studentList;
    }
    public List<String>getAllTeacher(){
        List<String>teacherList=new ArrayList<>();
        teacherList.addAll(teacherMap.keySet());
        return teacherList;
    }

    public void delete(Teacher teacher){
        teacherMap.remove(teacher.getName());
        stList.remove(teacher.getName());
        for(String student : teachList.keySet()){
            if(teachList.get(student).contains(teacher.getName())){
                List<String>teacherList=teachList.get(student);
                teacherList.remove(teacher.getName());
                teachList.put(student,teacherList);
            }
        }
    }

    public void delete(Student student){
        studentMap.remove(student.getName());
        stList.remove(student.getName());
        for(String teacher : stList.keySet()){
            if(stList.get(teacher).contains(student.getName())){
                List<String>studentList=stList.get(teacher);
                studentList.remove(student.getName());
                stList.put(teacher,studentList);
            }
        }
    }

    public void deleteAll(Teacher teacher){
        teacherMap.clear();
        stList.clear();
        teachList.clear();
    }
}
