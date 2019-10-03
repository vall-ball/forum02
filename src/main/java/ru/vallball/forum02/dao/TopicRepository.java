package ru.vallball.forum02.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.vallball.forum02.model.Topic;

public interface TopicRepository extends JpaRepository<Topic, String>{

}
