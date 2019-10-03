package ru.vallball.forum02.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.vallball.forum02.dao.TopicRepository;
import ru.vallball.forum02.model.Topic;

@Service
@Transactional
public class TopicServiceImpl implements TopicService{
	
	@Autowired
	TopicRepository topicRepository;
	
	@Override
	public void save(Topic topic) {
		topicRepository.save(topic);
		
	}

	@Override
	public List<Topic> list() {
		List<Topic> topics = topicRepository.findAll();
		Collections.sort(topics);
		return topics;
	}

	@Override
	public void delete(String id) {
		topicRepository.deleteById(id);
		
	}

	@Override
	public void update(Topic topic) {
		topicRepository.save(topic);
		
	}

	@Override
	public Topic findTopicById(String id) {
		return topicRepository.getOne(id);
	}

}
