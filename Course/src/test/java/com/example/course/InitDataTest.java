package com.example.course;


import com.example.course.entity.Course;
import com.example.course.entity.Image;
import com.example.course.entity.Topic;
import com.example.course.entity.User;
import com.example.course.repository.CourseRepository;
import com.example.course.repository.ImageRepository;
import com.example.course.repository.TopicRepository;
import com.example.course.repository.UserRepository;
import com.github.javafaker.Faker;
import com.github.slugify.Slugify;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootTest
public class InitDataTest {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private TopicRepository topicRepository;


    @Autowired
    private ImageRepository imageRepository;


    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private Faker faker;

    @Autowired
    private Slugify slugify;

    @Autowired
    private Random rd;


    @Test
    void save_user(){
        for (int i = 0; i < 10; i++) {
            User user = User.builder()
                    .name(faker.name().fullName())
                    .email(faker.internet().emailAddress())
                    .phone(faker.phoneNumber().phoneNumber())
                    .avatar(faker.company().logo())
                    .build();

            userRepository.save(user);
        }
    }

    @Test
    void save_image(){
        for (int i=0;i<10;i++){
            Image image = Image.builder()
                    .link(faker.company().logo())
                    .build();

            imageRepository.save(image);
        }
    }


    @Test
    void save_topic() {
        for (int i=0;i<20;i++){
            Topic topic = Topic.builder()
                    .name(faker.funnyName().name())
                    .build();

            topicRepository.save(topic);
        }
    }

    @Test
    void save_courses(){
        List<User> users = userRepository.findAll();
        List<Topic> topics = topicRepository.findAll();
        List<String> type = new ArrayList<>();
        type.add("online");
        type.add("onlab");

        for (int i=0;i<20;i++){
            List<Topic> listTopic = new ArrayList<>();
            for (int j = 0;j< 3;j++){
                Topic topicRd = topics.get(rd.nextInt(topics.size()));
                if (!listTopic.contains(topicRd)){
                    listTopic.add(topicRd);
                }
            }
            User userRd = users.get(rd.nextInt(users.size()));

            Course course = Course.builder()
                    .name(faker.lorem().sentence(10))
                    .slug(faker.lorem().sentence(10))
                    .type(type.get(rd.nextInt(type.size())))
                    .description(faker.lorem().paragraph(10))
                    .thumbnail(faker.company().logo())
                    .user(userRd)
                    .topics(listTopic)
                    .build();
            courseRepository.save(course);
        }

    }
}
