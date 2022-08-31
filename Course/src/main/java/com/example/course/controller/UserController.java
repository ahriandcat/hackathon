package com.example.course.controller;

import com.example.course.entity.Course;
import com.example.course.service.CourseService;
import com.example.course.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class UserController {
    @Autowired
    private CourseService courseService;

    @Autowired
    private TopicService topicService;


    @GetMapping ("/index")
    public String getUserCourseList(Model model, Optional<Integer> currentPage) {
        int pageNo = currentPage.orElse(0);
        Page<Course> page = courseService.getAllCourses(pageNo);
        System.out.println("test=================== "+ page.getTotalPages());
        model.addAttribute("courseList",page);
        model.addAttribute("topicList",topicService.getAllTopics());
        return "template-course/course-list";
    }
}
