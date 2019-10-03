package ru.vallball.forum02.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.vallball.forum02.dao.MessageRepository;
import ru.vallball.forum02.model.Message;

@Service
@Transactional
public class MessageServiceImpl implements MessageService{

	@Autowired
	MessageRepository messageDao;
	
	@Override
	public void save(Message message) {
		messageDao.save(message);
		
	}

	@Override
	public List<Message> list(String topic) {
		List<Message> mess = messageDao.findAll();
		List<Message> messages = new ArrayList<>();
		for (Message m : mess) {
			if (m.getTopic().getTopicname().equals(topic)) messages.add(m);
		}
		Collections.sort(messages);
		return messages;
	}

	@Override
	public void delete(Long id) {
		messageDao.deleteById(id);
		
	}

	@Override
	public void update(Message message) {
		messageDao.save(message);
		
	}

	@Override
	public Message findMessageById(Long id) {
		Page page;
		return messageDao.getOne(id);
	}

	
	

}

