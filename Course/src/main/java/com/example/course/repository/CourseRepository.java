package com.example.course.repository;

import com.example.course.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {

    //main page
    Page<Course> findByNameContainsIgnoreCase(@NonNull String name, Pageable pageable);

    @Query("select distinct course " +
            "from Course course " +
            "join course.topics topic " +
            "join topic.courses " +
            "where course.name like concat('%',:name,'%') and topic.id = :topicId ")
    Page<Course> findCoursesByTopicId(@Param("topicId") Long topicId, @Param("name") String name, Pageable pageable);


    @Query("select distinct course from Course course where course.id = :id")
    Course findCourseById(Long id);


    //onlab page

    @Query("select c from Course c where upper(c.type) like upper(?1) and upper(c.name) like upper(concat('%', ?2, '%'))")
    Page<Course> findByTypeEqualsOnlabAndNameContainsIgnoreCase(@NonNull String type, String name, Pageable pageable);

    @Query("select distinct course " +
            "from Course course " +
            "join course.topics topic " +
            "join topic.courses " +
            "where course.name like concat('%',:name,'%') and topic.id = :topicId and course.type like concat('%','onlab','%')")
    Page<Course> findCoursesByTopicIdWithTypeOnlab(@Param("topicId") Long topicId, @Param("name") String name, Pageable pageable);

}
