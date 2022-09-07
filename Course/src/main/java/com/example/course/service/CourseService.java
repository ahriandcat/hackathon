package com.example.course.service;

import com.example.course.entity.Course;
import com.example.course.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;
    public Page<Course> findByNameContainsIgnoreCase(Long topicId,String name, int pageNo) {
        Pageable pageable = PageRequest.of(pageNo, 9);
        if(topicId == 0){
            return courseRepository.findByNameContainsIgnoreCase(name,pageable);
        }
        return courseRepository.findCoursesByTopicId(topicId,name,pageable);
    }

    public Course findCourseById(Long id) {
        return courseRepository.findCourseById(id);
    }
}
