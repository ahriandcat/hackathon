package com.example.course.service;

import com.example.course.entity.Course;
import com.example.course.repository.CourseRepository;
import com.example.course.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public Page<Course> getAllCourses(int pageNo) {
        Pageable pageable = PageRequest.of(pageNo, 9);
        return courseRepository.findAll(pageable);
    }
}
