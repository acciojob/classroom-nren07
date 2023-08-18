package com.driver.Service;

import com.driver.Repository.StudentRepository;
import com.driver.Repository.TeacherRepository;
import com.driver.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private StudentRepository studentRepository;

    public void addTeacher(Teacher teacher) throws  Exception{
        if(teacher==null) throw new Exception("Teacher is null object");
        teacherRepository.save(teacher);
    }

    public Teacher getTeacherByName(String teacherName){
        if(teacherRepository.isAvailable(teacherName)) return teacherRepository.getTeacher(teacherName);
        else return null;
    }

    public void deleteTeacher(String teacherName)throws Exception{
        if(!teacherRepository.isAvailable(teacherName)) throw new Exception("teacher is not found");

    }

    public void deleteAll(){
        teacherRepository.deleteAll();
        studentRepository.deleteAllTeacher();
    }
}
