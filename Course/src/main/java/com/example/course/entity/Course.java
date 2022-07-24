package com.example.course.entity;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "slug", nullable = false)
    private String slug;
    @Column(name = "type", nullable = false)
    private String type;
    @Column(name = "description", nullable = false,columnDefinition = "TEXT")
    private String description;
    @Column(name = "thumbnail", nullable = false)
    private String thumbnail;

    @ManyToOne
    @JoinColumn(name = "supporter_id")
    private User user;

    @ManyToMany
    @JoinTable(name = "course_topic",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "topics_id"))
    private List<Topic> topics = new ArrayList<>();

}
