package com.example.course.controller;

import com.example.course.service.CourseService;
import com.example.course.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @Autowired
    private CourseService courseService;

    @Autowired
    private TopicService topicService;


    @GetMapping ("/")
    public String getUserCourseList(Model model) {
        model.addAttribute("courseList",courseService.getAllCourses());
        model.addAttribute("topicList",topicService.getAllTopics());
        return "template-course/course-list";
    }
}
