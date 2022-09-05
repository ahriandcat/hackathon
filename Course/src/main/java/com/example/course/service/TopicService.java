package com.example.course.service;

import com.example.course.entity.Course;
import com.example.course.entity.Topic;
import com.example.course.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    public List<Topic> getAllTopics() {
        return topicRepository.findAll();
    }

    public List<Course> getCoursesByTopicId(Long topicId) {
        Optional<Topic> nullAbleTopic  = topicRepository.findById(topicId);
        if (nullAbleTopic.isPresent()){
            Topic topic = nullAbleTopic.get();
            return topic.getCourses();
        }
        else {
            throw new RuntimeException();
        }
    }
}
