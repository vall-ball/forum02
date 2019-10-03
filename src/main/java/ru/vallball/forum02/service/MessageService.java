package ru.vallball.forum02.service;

import java.util.List;

import ru.vallball.forum02.model.Message;

public interface MessageService {
	void save(Message message);
	List<Message> list(String topic);
	void delete(Long id);
	void update(Message message);
	Message findMessageById(Long id);
}
