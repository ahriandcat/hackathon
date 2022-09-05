package com.example.course.controller;

import com.example.course.entity.Course;
import com.example.course.service.CourseService;
import com.example.course.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import java.util.Optional;

@Controller
public class UserController {
    @Autowired
    private CourseService courseService;

    @Autowired
    private TopicService topicService;


    @GetMapping ("/index")
    public String getUserCourseList(Model model,@RequestParam(required = false) Map<String, String> qparams) {
        int pageNo = 0;
        String searchName = "";
        String searchTopic = "";
        if (qparams.get("pageNo") != null){
            pageNo = Integer.parseInt(qparams.get("pageNo"));
        }
        if (qparams.get("searchName") != null){
            searchName = qparams.get("searchName");
        }
        if (qparams.get("searchTopic") != null){
            searchTopic = qparams.get("searchTopic");
        }
//        Page<Course> page = courseService.getAllCourses(pageNo);
        Pageable pageable = PageRequest.of(pageNo, 9);
        Page<Course> courseListByName = courseService.findByNameContainsIgnoreCase(searchName,pageable);
//        System.out.println("test=================== "+ page.getTotalPages());
        model.addAttribute("courseList",courseListByName);
        model.addAttribute("topicList",topicService.getAllTopics());
        return "template-course/course-list";
    }
}
