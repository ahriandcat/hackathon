package com.example.course.repository;

import com.example.course.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
    @Override
    Optional<Topic> findById(Long id);
}
