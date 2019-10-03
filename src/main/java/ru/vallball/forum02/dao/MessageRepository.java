package ru.vallball.forum02.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.vallball.forum02.model.Message;

public interface MessageRepository extends JpaRepository<Message, Long>{

}
