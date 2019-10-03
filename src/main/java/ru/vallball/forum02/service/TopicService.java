package ru.vallball.forum02.service;

import java.util.List;

import ru.vallball.forum02.model.Topic;

public interface TopicService {
	void save(Topic topic);
	List<Topic> list();
	void delete(String id);
	void update(Topic topic);
	Topic findTopicById(String id);
}
